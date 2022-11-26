package ewm.mapper;

import ewm.model.Event;
import ewm.model.NewEventDto;
import ewm.model.UpdateEventRequest;

public class EventMapper {

    public static Event newEventDtoToEvent(NewEventDto newEventDto) {
        Event event = new Event();
        event.setAnnotation(newEventDto.getAnnotation());
        event.setDescription(newEventDto.getDescription());
        event.setEventDate(newEventDto.getEventDate());
        event.setLocation(newEventDto.getLocation());
        event.setPaid(newEventDto.getPaid());
        event.setParticipantLimit(newEventDto.getParticipantLimit());
        event.setRequestModeration(newEventDto.getRequestModeration());
        event.setTitle(newEventDto.getTitle());
        return event;
    }

    public static Event updateEventRequestToEvent(UpdateEventRequest updateEventRequest) {
        Event event = new Event();
        event.setId(updateEventRequest.getEventId());
        event.setAnnotation(updateEventRequest.getAnnotation());
        event.setCategoryId(updateEventRequest.getCategory());
        event.setDescription(updateEventRequest.getDescription());
        event.setEventDate(updateEventRequest.getEventDate());
        event.setPaid(updateEventRequest.getPaid());
        event.setParticipantLimit(updateEventRequest.getParticipantLimit());
        event.setTitle(updateEventRequest.getTitle());
        return event;
    }

    public static NewEventDto eventFullDtoToNewEventDto(Event event) {
        NewEventDto newEventDto = new NewEventDto();
        newEventDto.setAnnotation(event.getAnnotation());
        newEventDto.setCategoryId(event.getCategoryId());
        newEventDto.setDescription(event.getDescription());
        newEventDto.setEventDate(event.getEventDate());
        newEventDto.setLocation(event.getLocation());
        newEventDto.setPaid(event.getPaid());
        newEventDto.setParticipantLimit(event.getParticipantLimit());
        newEventDto.setRequestModeration(event.getRequestModeration());
        newEventDto.setTitle(event.getTitle());
        return newEventDto;
    }

}
