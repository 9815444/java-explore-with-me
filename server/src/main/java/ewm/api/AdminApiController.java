package ewm.api;

import ewm.model.*;
import ewm.service.CategoryService;
import ewm.service.EventService;
import ewm.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class AdminApiController {

    private final UserService userService;

    private final EventService eventService;

    private final CategoryService categoryService;

    //Users {

    @RequestMapping(value = "/admin/users",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    public ResponseEntity<User> registerUser(@Valid @RequestBody NewUserDto newUserDto) {
        var userDto = userService.registerUser(newUserDto);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/users/{userId}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "/admin/users",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUsers(
            @Valid @RequestParam(value = "ids", required = false) List<Long> ids,
            @Valid @RequestParam(value = "from", required = false, defaultValue = "0") Integer from,
            @Valid @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        var users = userService.getUsers(ids, from, size);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    //}Users


    //Categories {
    @RequestMapping(value = "/admin/categories/{catId}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCategory(@PathVariable("catId") Long catId) {
        categoryService.deleteCategory(catId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "/admin/categories",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    public ResponseEntity<Category> addCategory(@Valid @RequestBody NewCategoryDto body) {
        return new ResponseEntity<>(categoryService.addCategory(body), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/categories",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.PATCH)
    public ResponseEntity<Category> updateCategory(@Valid @RequestBody Category body) {
        return new ResponseEntity<>(categoryService.updateCategory(body), HttpStatus.OK);
    }

    //} Categories

    //Compilation {

    @RequestMapping(value = "/admin/compilations/{compId}/events/{eventId}",
            produces = {"application/json"},
            method = RequestMethod.PATCH)
    ResponseEntity<Void> addEventToCompilation(@PathVariable("compId") Long compId,
                                               @PathVariable("eventId") Long eventId) {
        eventService.addEventToCompilation(compId, eventId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/compilations/{compId}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCompilation(@PathVariable("compId") Long compId) {
        eventService.deleteCompilation(compId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/compilations/{compId}/events/{eventId}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    public ResponseEntity<Void> removeEventFromCompilation(
            @PathVariable("compId") Long compId,
            @PathVariable("eventId") Long eventId) {
        eventService.removeEventFromCompilation(compId, eventId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    @RequestMapping(value = "/admin/compilations",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    public ResponseEntity<Compilation> addCompilation(
            @Valid @RequestBody NewCompilationDto newCompilationDto) {
        var compilation = eventService.addCompilation(newCompilationDto);
        return new ResponseEntity<Compilation>(compilation, HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/compilations/{compId}/pin",
            produces = {"application/json"},
            method = RequestMethod.PATCH)
    public ResponseEntity<Void> pin(@PathVariable("compId") Long compId) {
        eventService.pin(compId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/compilations/{compId}/pin",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    public ResponseEntity<Void> unpin(@PathVariable("compId") Long compId) {
        eventService.unpin(compId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    //} Compilation

    //Events {
    @RequestMapping(value = "/admin/events/{eventId}",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.PUT)
    public ResponseEntity<Event> updateEvent(@PathVariable("eventId") Long eventId,
                                             @Valid @RequestBody AdminUpdateEventRequest body) {
        return new ResponseEntity<>(eventService.updateEventAdmin(eventId, body), HttpStatus.OK);
    }


    @RequestMapping(value = "/admin/events",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<List<Event>> getEvents(
            @Valid @RequestParam(value = "users", required = false) List<Long> users,
            @Valid @RequestParam(value = "states", required = false) List<String> states,
            @Valid @RequestParam(value = "categories", required = false) List<Long> categories,

            @Valid @RequestParam(value = "rangeStart", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeStart,

            @Valid @RequestParam(value = "rangeEnd", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeEnd,

            @Valid @RequestParam(value = "from", required = false, defaultValue = "0") Integer from,
            @Valid @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        var events = eventService.getEvents(users, states, categories, rangeStart, rangeEnd, from, size);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    //} Events


    @RequestMapping(value = "/admin/events/{eventId}/publish",
            produces = {"application/json"},
            method = RequestMethod.PATCH)
    public ResponseEntity<Event> publishEvent(@PathVariable("eventId") Long eventId) {
        var event = eventService.publishEvent(eventId);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }


    @RequestMapping(value = "/admin/events/{eventId}/reject",
            produces = {"application/json"},
            method = RequestMethod.PATCH)
    public ResponseEntity<Event> rejectEvent(@PathVariable("eventId") Long eventId) {
        var eventFullDtoOptional = eventService.rejectEvent(eventId);
        return new ResponseEntity<>(eventFullDtoOptional, HttpStatus.OK);
    }


}
