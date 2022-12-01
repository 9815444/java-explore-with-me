package ewm.api;

import ewm.model.*;
import ewm.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
@Slf4j
public class UsersApiController {

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
    public ResponseEntity<Request> cancelRequest(@PathVariable("userId") Long userId,
                                                 @PathVariable("requestId") Long requestId) {
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

    //Comments { //todo comments private
    @RequestMapping(value = "/users/{userId}/events/{eventId}/comments",
            produces = {"application/json"},
            method = RequestMethod.POST)
    public ResponseEntity<Comment> addComment(
            @PathVariable("userId") Long userId,
            @PathVariable("eventId") Long eventId,
            @Valid @RequestBody NewCommentDto newCommentDto) {
        return new ResponseEntity<>(eventService.addComment(userId, eventId, newCommentDto), HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{userId}/events/{eventId}/comments",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<List<Comment>> findEventComments(
            @PathVariable("userId") Long userId,
            @PathVariable("eventId") Long eventId) {
        return new ResponseEntity<>(eventService.findEventComments(userId, eventId), HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{userId}/comments",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<List<Comment>> findAllComments(
            @PathVariable("userId") Long userId) {
        return new ResponseEntity<>(eventService.findAllComments(userId), HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{userId}/events/{eventId}/comments/{commentId}",
            produces = {"application/json"},
            method = RequestMethod.PATCH)
    public ResponseEntity<Comment> updateComment(
            @PathVariable("userId") Long userId,
            @PathVariable("eventId") Long eventId,
            @PathVariable("commentId") Long commentId,
            @Valid @RequestBody NewCommentDto newCommentDto) {
        return new ResponseEntity<>(eventService.updateComment(userId, eventId, commentId, newCommentDto), HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{userId}/events/{eventId}/comments/{commentId}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    public ResponseEntity<List<Request>> deleteComment(
            @PathVariable("userId") Long userId,
            @PathVariable("eventId") Long eventId,
            @PathVariable("commentId") Long commentId) {
        eventService.deleteComment(userId, eventId, commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //} Comments

}
