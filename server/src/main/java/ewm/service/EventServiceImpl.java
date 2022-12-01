package ewm.service;

import ewm.client.StatsClient;
import ewm.errors.BadRequestException;
import ewm.errors.NotFoundException;
import ewm.mapper.EventMapper;
import ewm.model.*;
import ewm.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final StatsClient statsClient;

    private final EventRepository eventRepository;

    private final CommentRepository commentRepository;

    private final RequestRepository requestRepository;

    private final CompilationRepository compilationRepository;

    private final CompilationEventRepository compilationEventRepository;

    private final CategoryRepository categoryRepository;

    private final LocationRepository locationRepository;

    private final UserRepository userRepository;

    @Override
    @Transactional
    public Event addEvent(Long userId, NewEventDto newEventDto) {

        if (newEventDto.getEventDate().isBefore(LocalDateTime.now().plusHours(2))) {
            throw new BadRequestException("Неверная дата начала");
        }

        var mappedEventFullDto = EventMapper.newEventDtoToEvent(newEventDto);
        mappedEventFullDto.setState(Event.StateEnum.PENDING);
        var locationOptional = locationRepository.findLocationByLatAndLon(
                mappedEventFullDto.getLocation().getLat(),
                mappedEventFullDto.getLocation().getLon()
        );

        Location location;
        if (locationOptional.isPresent()) {
            location = locationOptional.get();
        } else {
            location = locationRepository.save(mappedEventFullDto.getLocation());
        }

        mappedEventFullDto.setLocationId(location.getId());
        mappedEventFullDto.setLocation(location);

        mappedEventFullDto.setCreatedOn(LocalDateTime.now());
        mappedEventFullDto.setUserId(userId);
        var categoryEntity = categoryRepository.findById(
                newEventDto.getCategoryId()).orElseThrow(() -> new NotFoundException("Не найдена категория."));
        mappedEventFullDto.setCategory(categoryEntity);
        var initiator = userRepository.findById(userId).orElseThrow();
        mappedEventFullDto.setInitiator(initiator);

        var eventFullDto = eventRepository.save(mappedEventFullDto);
        return eventFullDto;
    }


    @Override
    public Event getEventPublic(Long id, String requestURI, String remoteAddr) {
        var event = eventRepository.findByIdAndState(id, Event.StateEnum.PUBLISHED).orElseThrow();
        var reqs = requestRepository.findAllByEventAndStatus(event.getId(), Request.StateEnum.CONFIRMED);
        event.setConfirmedRequests(Long.valueOf(reqs.size()));
        event.setComments(commentRepository.findAllByEventIdAndPublishedIsTrue(id));
        statsClient.addStatEntry(new StatEntry("ewm", requestURI, remoteAddr, LocalDateTime.now()));
        return event;
    }

    @Override
    public Event publishEvent(Long eventId) {
        var event = eventRepository.findById(eventId).orElseThrow(
                () -> new NotFoundException("Не найдено событие"));
        if (event.getState().equals(Event.StateEnum.PENDING)
                && event.getEventDate().isAfter(LocalDateTime.now().plusHours(1))) {
            event.setState(Event.StateEnum.PUBLISHED);
            event.setPublishedOn(LocalDateTime.now());
            var publishedEvent = eventRepository.save(event);
            return publishedEvent;
        } else {
            throw new BadRequestException("Событие не в статусе PENDING");
        }
    }

    @Override
    public Event rejectEvent(Long eventId) {
        var eventFullDto = eventRepository.findById(eventId).orElseThrow();
        if (eventFullDto.getState().equals(Event.StateEnum.PENDING)) {
            eventFullDto.setState(Event.StateEnum.CANCELED);
            var publishedEvent = eventRepository.save(eventFullDto);
            return publishedEvent;
        } else {
            throw new BadRequestException("Событие не в статусе PENDING");
        }
    }

    @Override
    public Event updateEventAdmin(Long eventId, AdminUpdateEventRequest newData) {
        var event = eventRepository.findById(eventId).orElseThrow(
                () -> new NotFoundException("Не найдено событие"));
        event.setAnnotation(newData.getAnnotation());

        var categoryId = newData.getCategory();
        var category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new NotFoundException("Не найдена категория"));

        event.setCategory(category);
        event.setCategoryId(categoryId);

        event.setDescription(newData.getDescription());
        event.setEventDate(newData.getEventDate());

        var newLocation = newData.getLocation();
        if (newLocation != null) {
            var locationOptional = locationRepository.findLocationByLatAndLon(
                    newData.getLocation().getLat(),
                    newData.getLocation().getLon()
            );
            Location location;
            if (locationOptional.isPresent()) {
                location = locationOptional.get();
            } else {
                location = locationRepository.save(newData.getLocation());
            }

            event.setLocationId(location.getId());
            event.setLocation(location);
        }

        event.setPaid(newData.getPaid());
        event.setParticipantLimit(newData.getParticipantLimit());
        event.setRequestModeration(newData.getRequestModeration());
        event.setTitle(newData.getTitle());

        return eventRepository.save(event);
    }

    @Override
    public List<Event> getEvents(
            List<Long> users,
            List<String> states,
            List<Long> categories,
            LocalDateTime rangeStart,
            LocalDateTime rangeEnd,
            Integer from,
            Integer size) {
        int fromPage = from.intValue() / size.intValue();
        Pageable pageable = PageRequest.of(fromPage, size.intValue());
        List<Event.StateEnum> stateEnumList;
        if (states == null) {
            stateEnumList = new ArrayList<>();
        } else {
            stateEnumList = states.stream().map(s -> Event.StateEnum.valueOf(s)).collect(Collectors.toList());
        }
        return eventRepository.getEvents(
                users,
                users.size() == 0,
                stateEnumList,
                stateEnumList.size() == 0,
                rangeStart,
                rangeStart == null,
                rangeEnd,
                rangeEnd == null,
                pageable);
    }


    @Override
    public List<Event> getAllEvents(
            String text,
            List<Long> categories,
            Boolean paid,
            LocalDateTime rangeStart,
            LocalDateTime rangeEnd,
            Boolean onlyAvailable,
            String sort,
            Integer from,
            Integer size,
            String requestURI,
            String remoteAddr) {
        statsClient.addStatEntry(new StatEntry("ewm", requestURI, remoteAddr, LocalDateTime.now()));
        int fromPage = from.intValue() / size.intValue(); //todo добавить количество просмотров
        Pageable pageable = PageRequest.of(fromPage, size.intValue());
        if (sort == null) {
            sort = "EVENT_DATE";
        }
        var events = eventRepository.getAllEvents(
                text,
                categories,
                paid,
                rangeStart,
                rangeStart == null,
                rangeEnd,
                rangeEnd == null,
                LocalDateTime.now(),
                pageable);
        for (Event event : events) {
            var reqs = requestRepository.findAllByEventAndStatus(event.getId(),
                    Request.StateEnum.CONFIRMED);
            event.setConfirmedRequests(Long.valueOf(reqs.size()));
            event.setComments(commentRepository.findAllByEventIdAndPublishedIsTrue(event.getId()));
        }
        List<Event> result;
        if (onlyAvailable) {
            result = events
                    .stream()
                    .filter(event -> participantLimitNotReached(event))
                    .collect(Collectors.toList());

        } else {
            result = events;
        }
        if (sort.equals("EVENT_DATE")) {
            return result.stream()
                    .sorted((e1, e2) -> {
                        return e1.getEventDate().compareTo(e2.getEventDate());
                    })
                    .collect(Collectors.toList());
        } else {
            return result.stream()
                    .sorted((e1, e2) -> {
                        return e1.getViews().compareTo(e2.getViews());
                    })
                    .collect(Collectors.toList());
        }
    }

    private boolean participantLimitNotReached(Event event) {
        if (event.getParticipantLimit() == 0) {
            return true;
        } else {
            return event.getParticipantLimit() > event.getConfirmedRequests();
        }
    }


    @Override
    public ResponseEntity<Request> addRequest(Long userId, Long eventId) {
        var request = new Request();
        request.setCreated(LocalDateTime.now());
        request.setEvent(eventId);
        request.setRequester(userId);
        request.setStatus(Request.StateEnum.PENDING);
        var event = eventRepository.findById(eventId).orElseThrow(
                () -> new NotFoundException("Событие не найдено"));
        if (!event.getState().equals(Event.StateEnum.PUBLISHED)) {
            throw new BadRequestException("Событие не опубликовано");
        }
        if (event.getUserId().equals(userId)) {
            throw new BadRequestException("Пользователь не может отправлять запросы на свое событие");
        }
        if (manyRequests(event)) {
            throw new BadRequestException("Превышение лимита по количеству запросов");
        }
        if (requestRepository.findByEventAndRequester(eventId, userId).isPresent()) {
            throw new BadRequestException("Запрос был отправлен ранее");
        }
        if (!event.getRequestModeration()) {
            request.setStatus(Request.StateEnum.CONFIRMED);
        }
        var addedRequest = requestRepository.save(request);
        return new ResponseEntity<>(addedRequest, HttpStatus.OK);
    }

    @Override
    public Request confirmRequest(Long userId, Long eventId, Long reqId) {
        var event = eventRepository.findById(eventId).orElseThrow(
                () -> new NotFoundException("Событие не найдено"));
        var user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("Пользователь не найден"));
        var req = requestRepository.findById(reqId).orElseThrow(
                () -> new NotFoundException("Запрос не найден"));
        if (event.getParticipantLimit() == 0) {
            throw new BadRequestException("Подтверждение не требуется");
        }
        if (manyRequests(event)) {
            var pendingReq = requestRepository.findAllByEventAndStatus(eventId, Request.StateEnum.PENDING);
            for (Request request : pendingReq) {
                request.setStatus(Request.StateEnum.REJECTED);
                requestRepository.save(request);
            }
            throw new BadRequestException("Превышение лимита по количеству запросов");
        }
        req.setStatus(Request.StateEnum.CONFIRMED);
        return requestRepository.save(req);
    }

    @Override
    public Request rejectRequest(Long userId, Long eventId, Long reqId) {
        var event = eventRepository.findById(eventId).orElseThrow(
                () -> new NotFoundException("Событие не найдено"));
        var user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("Пользователь не найден"));
        var req = requestRepository.findById(reqId).orElseThrow(
                () -> new NotFoundException("Запрос не найден"));
        if (event.getParticipantLimit() == 0) {
            throw new BadRequestException("Подтверждение не требуется");
        }
        req.setStatus(Request.StateEnum.REJECTED);
        return requestRepository.save(req);
    }

    @Override
    public Request cancelRequest(Long userId, Long requestId) {
        var req = requestRepository.findById(requestId).orElseThrow(
                () -> new NotFoundException("Не найден запрос"));
        if (!(req.getRequester().equals(userId))) {
            throw new BadRequestException("Пользователь не является автором запроса");
        }
        req.setStatus(Request.StateEnum.CANCELED);
        return requestRepository.save(req);
    }

    @Override
    public Event cancelEvent(Long userId, Long eventId) {
        var event = eventRepository.findById(eventId).orElseThrow(
                () -> new NotFoundException("Событие не найдено"));
        if (!(event.getUserId().equals(userId))) {
            throw new BadRequestException("Пользователь не является автором события");
        }
        if (!event.getState().equals(Event.StateEnum.PENDING)) {
            throw new BadRequestException("Событие в статусе отличным от PENDING. Отмена невозможна");
        }
        event.setState(Event.StateEnum.CANCELED);
        return eventRepository.save(event);
    }

    @Override
    public Event getEvent(Long eventId) {
        var event = eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException("Событие не найдено"));
        var reqs = requestRepository.findAllByEventAndStatus(event.getId(), Request.StateEnum.CONFIRMED);
        event.setConfirmedRequests(Long.valueOf(reqs.size()));
        event.setComments(commentRepository.findAllByEventIdAndPublishedIsTrue(eventId));
        return event;
    }

    @Override
    public List<Request> getUserEventRequests(Long userId, Long eventId) {
        var event = eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException("Событие не найдено"));
        if (!event.getUserId().equals(userId)) {
            throw new BadRequestException("Это событие другого пользователя");
        }
        return requestRepository.findAllByEvent(eventId);
    }

    @Override
    public List<Event> getUserEvents(Long userId, Integer from, Integer size) {
        int fromPage = from.intValue() / size.intValue();
        Pageable pageable = PageRequest.of(fromPage, size.intValue());
        var events = eventRepository.findAllByUserId(userId, pageable);
        for (Event event : events) {
            var reqs = requestRepository
                    .findAllByEventAndStatus(event.getId(), Request.StateEnum.CONFIRMED);
            event.setConfirmedRequests(Long.valueOf(reqs.size()));
        }
        return events;
    }

    @Override
    public List<Request> getUserRequests(Long userId) {
        return requestRepository.findAllByRequester(userId);
    }

    @Override
    public Event updateEvent(Long userId, UpdateEventRequest updateEventRequest) {

        var event = eventRepository.findById(updateEventRequest.getEventId()).orElseThrow(
                () -> new NotFoundException("Не найдено событие"));

        event.setAnnotation(updateEventRequest.getAnnotation());
        event.setDescription(updateEventRequest.getDescription());
        event.setEventDate(updateEventRequest.getEventDate());

        var categoryId = updateEventRequest.getCategory();
        var category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new NotFoundException("Не найдена категория"));

        event.setCategory(category);
        event.setCategoryId(categoryId);

        event.setDescription(updateEventRequest.getDescription());
        event.setEventDate(updateEventRequest.getEventDate());

        event.setPaid(updateEventRequest.getPaid());
        event.setParticipantLimit(updateEventRequest.getParticipantLimit());
        event.setTitle(updateEventRequest.getTitle());

        return eventRepository.save(event);
    }

    @Override
    @Transactional
    public Compilation addCompilation(NewCompilationDto newCompilationDto) {
        Compilation compilation = new Compilation();
        compilation.setTitle(newCompilationDto.getTitle());
        compilation.setPinned(newCompilationDto.getPinned());
        List<Event> events = new ArrayList<>();
        for (Long eventId : newCompilationDto.getEvents()) {
            events.add(
                    eventRepository.findById(eventId)
                            .orElseThrow(() -> new NotFoundException("Событие не найдено"))
            );
        }
        compilation.setEvents(events);
        Compilation newcompilaton = compilationRepository.save(compilation);
        for (Long eventId : newCompilationDto.getEvents()) {
            CompilationEvent compilationEvent = new CompilationEvent();
            compilationEvent.setEventId(eventId);
            compilationEvent.setCompilationId(newcompilaton.getId());
            compilationEventRepository.save(compilationEvent);
        }

        return compilation;
    }

    @Override
    public void deleteCompilation(Long compId) {
        Compilation compilation = compilationRepository.findById(compId)
                .orElseThrow(() -> new NotFoundException("Подборка не найдена"));
        var compilationEvent = compilationEventRepository.findAllByCompilationId(compId);
        for (CompilationEvent eventIncComp : compilationEvent) {
            compilationEventRepository.deleteById(eventIncComp.getId());
        }
        compilationRepository.deleteById(compId);
    }

    @Override
    public Compilation getCompilation(Long compId) {
        Compilation compilation = compilationRepository.findById(compId)
                .orElseThrow(() -> new NotFoundException("Подборка не найдена"));
        var events = compilationEventRepository.findAllByCompilationId(compId);
        compilation.setEvents(
                events.stream().map((e) -> {
                    var eventObject = eventRepository.findById(e.getEventId())
                            .orElseThrow(() -> new NotFoundException("Не найдено событие"));
                    eventObject.setComments(commentRepository.findAllByEventIdAndPublishedIsTrue(e.getEventId()));
                    return eventObject;
                }).collect(Collectors.toList()));
        return compilation;
    }

    @Override
    public void pin(Long compId) {
        pinUnpin(compId, true);
    }

    @Override
    public void unpin(Long compId) {
        pinUnpin(compId, false);
    }

    @Override
    public void addEventToCompilation(Long compId, Long eventId) {
        var comp = compilationRepository.findById(compId)
                .orElseThrow(() -> new NotFoundException("Подборка не найдена"));
        var event = eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException("Событие не найдено"));
        CompilationEvent compilationEvent = new CompilationEvent();
        compilationEvent.setCompilationId(compId);
        compilationEvent.setEventId(eventId);
        compilationEventRepository.save(compilationEvent);
    }

    @Override
    public void removeEventFromCompilation(Long compId, Long eventId) {
        var comp = compilationRepository.findById(compId)
                .orElseThrow(() -> new NotFoundException("Подборка не найдена"));
        var event = eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException("Событие не найдено"));
        List<CompilationEvent> compilationEventList =
                compilationEventRepository.findAllByCompilationIdAndEventId(compId, eventId);
        compilationEventRepository.deleteAllById(compilationEventList.stream().map(
                (e) -> e.getId()).collect(Collectors.toList()));
    }

    @Override
    public List<Compilation> getCompilations(Boolean pinned, Integer from, Integer size) {
        int fromPage = from.intValue() / size.intValue();
        Pageable pageable = PageRequest.of(fromPage, size.intValue());
        List<Compilation> comps;
        if (pinned == null) {
            comps = compilationRepository.findAll(pageable).getContent();
        } else {
            comps = compilationRepository.findAllByPinned(pinned, pageable);
        }
        for (Compilation comp : comps) {
            var eventsIdList = compilationEventRepository.findAllByCompilationId(comp.getId()).stream()
                    .map(compilationEvent -> compilationEvent.getEventId()).collect(Collectors.toList());
            var events = eventRepository.findAllById(eventsIdList)
                    .stream().map((event -> {
                        event.setComments(commentRepository.findAllByEventIdAndPublishedIsTrue(event.getId()));
                        return event;
                    })).collect(Collectors.toList());
            comp.setEvents(events);

        }
        return comps;
    }

    public void pinUnpin(Long compId, boolean pinned) {
        var comp = compilationRepository.findById(compId)
                .orElseThrow(() -> new NotFoundException("Подборка не найдена"));
        comp.setPinned(pinned);
        compilationRepository.save(comp);
    }


    boolean manyRequests(Event event) {
        var requests = requestRepository
                .findAllByEventAndStatus(event.getId(), Request.StateEnum.CONFIRMED);
        var numberOfRequests = Integer.valueOf(requests.size());
        var limit = event.getParticipantLimit();
        if ((limit > 0) && (numberOfRequests >= limit)) {
            return true;
        } else {
            return false;
        }
    }

    //Comments{
    @Override
    public Comment addComment(Long userId, Long eventId, NewCommentDto newCommentDto) {
        checkEventAndUser(userId, eventId);
        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setEventId(eventId);
        comment.setText(newCommentDto.getText());
        comment.setPublished(false);
        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(Long userId, Long eventId, Long commentId, NewCommentDto newCommentDto) {
        checkEventAndUser(userId, eventId);
        var comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Комментарий не найден"));
        checkComment(userId, eventId, comment);
        if (comment.getPublished()) {
            throw new BadRequestException("Комментарий уже опубликован");
        }
        comment.setPublished(false);
        comment.setText(newCommentDto.getText());
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long userId, Long eventId, Long commentId) {
        checkEventAndUser(userId, eventId);
        var comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Комментарий не найден"));
        checkComment(userId, eventId, comment);
        commentRepository.deleteById(commentId);
    }

    @Override
    public void postComment(Long commentId) {
        var comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Комментарий не найден"));
        if (comment.getPublished()) {
            throw new BadRequestException("Комментарий был опубликован ранее");
        }
        comment.setPublished(true);
        commentRepository.save(comment);
    }

    @Override
    public void deleteCommentByAdmin(Long commentId) {
        var comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Комментарий не найден"));
        commentRepository.deleteById(commentId);
    }

    @Override
    public List<Comment> findEventComments(Long userId, Long eventId) {
        var event = eventRepository.findById(eventId).orElseThrow(
                () -> new NotFoundException("Событие не найдено"));
        var user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("Пользователь не найден"));
        return commentRepository.findAllByUserIdAndEventId(userId, eventId);
    }

    @Override
    public List<Comment> findAllComments(Long userId) {
        var user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("Пользователь не найден"));
        return commentRepository.findAllByUserId(userId);
    }

    private void checkEventAndUser(Long userId, Long eventId) {
        var event = eventRepository.findByIdAndState(eventId, Event.StateEnum.PUBLISHED).orElseThrow(
                () -> new NotFoundException("Событие не найдено"));
        var user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("Пользователь не найден"));
    }

    private static void checkComment(Long userId, Long eventId, Comment comment) {
        if (!(comment.getUserId().equals(userId))) {
            throw new BadRequestException("Чужой комментарий");
        }
        if (!(comment.getEventId().equals(eventId))) {
            throw new BadRequestException("Это комментарий к другому событию");
        }
    }
    //}Comments

}
