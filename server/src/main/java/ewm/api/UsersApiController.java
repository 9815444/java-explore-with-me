package ewm.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import ewm.model.Event;
import ewm.model.NewEventDto;
import ewm.model.Request;
import ewm.model.UpdateEventRequest;
import ewm.service.EventService;
import ewm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class UsersApiController {

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final UserService userService;

    private final EventService eventService;

    @RequestMapping(value = "/users/{userId}/requests",
            produces = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Request> addRequest(
            @PathVariable("userId") Long userId,
            @NotNull @Valid @RequestParam(value = "eventId", required = true) Long eventId) {
        return eventService.addRequest(userId, eventId);

    }

    @RequestMapping(value = "/users/{userId}/events",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    public ResponseEntity<Event> addEvent(
            @PathVariable("userId") Long userId,
            @Valid @RequestBody NewEventDto body) {
        return new ResponseEntity<>(eventService.addEvent(userId, body), HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{userId}/events/{eventId}/requests/{reqId}/confirm",
            produces = {"application/json"},
            method = RequestMethod.PATCH)
    public ResponseEntity<Request> confirmRequest(
            @PathVariable("userId") Long userId,
            @PathVariable("eventId") Long eventId,
            @PathVariable("reqId") Long reqId) {
        return new ResponseEntity<Request>(eventService.confirmRequest(userId, eventId, reqId), HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{userId}/events/{eventId}/requests/{reqId}/reject",
            produces = {"application/json"},
            method = RequestMethod.PATCH)
    public ResponseEntity<Request> rejectRequest(
            @PathVariable("userId") Long userId,
            @PathVariable("eventId") Long eventId,
            @PathVariable("reqId") Long reqId) {
        return new ResponseEntity<Request>(eventService.rejectRequest(userId, eventId, reqId), HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{userId}/events/{eventId}",
            produces = {"application/json"},
            method = RequestMethod.PATCH)
    public ResponseEntity<Event> cancelEvent(@PathVariable("userId") Long userId,
                                             @PathVariable("eventId") Long eventId) {
        Event event = eventService.cancelEvent(userId, eventId);
        return new ResponseEntity<Event>(event, HttpStatus.OK);
    }


    @RequestMapping(value = "/users/{userId}/requests/{requestId}/cancel",
            produces = {"application/json"},
            method = RequestMethod.PATCH)
    public ResponseEntity<Request> cancelRequest(@PathVariable("userId") Long userId, @PathVariable("requestId") Long requestId) {
        var req = eventService.cancelRequest(userId, requestId);
        return new ResponseEntity<Request>(req, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{userId}/events/{eventId}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<Event> getEvent(
            @PathVariable("userId") Long userId,
            @PathVariable("eventId") Long eventId) {
        return new ResponseEntity<Event>(eventService.getEvent(eventId), HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{userId}/events/{eventId}/requests",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<List<Request>> getEventParticipants(
            @PathVariable("userId") Long userId,
            @PathVariable("eventId") Long eventId) {
        var requests = eventService.getUserEventRequests(userId, eventId);
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{userId}/events",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<List<Event>> getEvents(
            @PathVariable("userId") Long userId,
            @Valid @RequestParam(value = "from", required = false, defaultValue = "0") Integer from,
            @Valid @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        return new ResponseEntity<List<Event>>(eventService.getUserEvents(userId, from, size),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{userId}/requests",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<List<Request>> getUserRequests(@PathVariable("userId") Long userId) {
        return new ResponseEntity<List<Request>>(eventService.getUserRequests(userId), HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{userId}/events",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.PATCH)
    public ResponseEntity<Event> updateEvent(
            @PathVariable("userId") Long userId,
            @Valid @RequestBody UpdateEventRequest updateEventRequest) {
        return new ResponseEntity<Event>(eventService.updateEvent(userId, updateEventRequest), HttpStatus.OK);
    }

}
