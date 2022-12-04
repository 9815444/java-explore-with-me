package ewm.service;

import ewm.model.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {
    Event addEvent(Long userId, NewEventDto body);

    Event getEventPublic(Long id, String requestURI, String remoteAddr);

    Event publishEvent(Long eventId);

    Event rejectEvent(Long eventId);

    Event updateEventAdmin(Long eventId, AdminUpdateEventRequest body);

    List<Event> getEvents(List<Long> users, List<String> states, List<Long> categories,
                          LocalDateTime rangeStart, LocalDateTime rangeEnd, Integer from, Integer size);

    List<Event> getAllEvents(String text, List<Long> categories, Boolean paid, LocalDateTime rangeStart,
                             LocalDateTime rangeEnd, Boolean onlyAvailable, String sort,
                             Integer from, Integer size, String requestURI, String remoteAddr);

    ResponseEntity<Request> addRequest(Long userId, Long eventId);

    Request confirmRequest(Long userId, Long eventId, Long reqId);

    Request rejectRequest(Long userId, Long eventId, Long reqId);

    Request cancelRequest(Long userId, Long requestId);

    Event cancelEvent(Long userId, Long eventId);

    Event getEvent(Long eventId);

    List<Request> getUserEventRequests(Long userId, Long eventId);

    List<Event> getUserEvents(Long userId, Integer from, Integer size);

    List<Request> getUserRequests(Long userId);

    Event updateEvent(Long userId, UpdateEventRequest body);

    Compilation addCompilation(NewCompilationDto newCompilationDto);

    void deleteCompilation(Long compId);

    Compilation getCompilation(Long compId);

    void pin(Long compId);

    void unpin(Long compId);

    void addEventToCompilation(Long compId, Long eventId);

    void removeEventFromCompilation(Long compId, Long eventId);

    List<Compilation> getCompilations(Boolean pinned, Integer from, Integer size);

    Comment addComment(Long userId, Long eventId, NewCommentDto newCommentDto);

    Comment updateComment(Long userId, Long eventId, Long commentId, NewCommentDto newCommentDto);

    void deleteComment(Long userId, Long eventId, Long commentId);

    void postComment(Long commentId);

    void deleteCommentByAdmin(Long commentId);

    List<Comment> findEventComments(Long userId, Long eventId);

    List<Comment> findAllUserComments(Long userId, Integer from, Integer size);

    List<Comment> findAllComments(Integer from, Integer size);
}
