package ewm.api;

import ewm.client.StatsClient;
import ewm.model.Event;
import ewm.model.StatEntry;
import ewm.service.EventService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class EventsApiController {

    private static final Logger log = LoggerFactory.getLogger(EventsApiController.class);

    private final EventService eventService;

    private final StatsClient statsClient;

    @RequestMapping(value = "/events/{id}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<Event> getEvent(@PathVariable("id") Long id, HttpServletRequest request) {
        var event = eventService.getEventPublic(id);
        statsClient.addStatEntry(new StatEntry("ewm", request.getRequestURI(), request.getRemoteAddr(), LocalDateTime.now()));
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @RequestMapping(value = "/events",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<List<Event>> getAllEvents(
            @Valid @RequestParam(value = "text", required = false) String text,
            @Valid @RequestParam(value = "categories", required = false) List<Long> categories,
            @Valid @RequestParam(value = "paid", required = false) Boolean paid,

            @Valid @RequestParam(value = "rangeStart", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeStart,

            @Valid @RequestParam(value = "rangeEnd", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeEnd,

            @Valid @RequestParam(value = "onlyAvailable", required = false, defaultValue = "false") Boolean onlyAvailable,
            @Valid @RequestParam(value = "sort", required = false) String sort,
            @Valid @RequestParam(value = "from", required = false, defaultValue = "0") Integer from,
            @Valid @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
            HttpServletRequest request) {
        var events = eventService.getAllEvents(text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size);
        statsClient.addStatEntry(new StatEntry("ewm", request.getRequestURI(), request.getRemoteAddr(), LocalDateTime.now()));
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

}
