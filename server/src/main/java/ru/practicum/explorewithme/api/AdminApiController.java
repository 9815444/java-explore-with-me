package ru.practicum.explorewithme.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.explorewithme.model.*;
import ru.practicum.explorewithme.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

//@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-11-13T14:31:14.091Z[GMT]")
@RestController
@RequiredArgsConstructor
public class AdminApiController implements AdminApi {

    private final UserService userService;

    private static final Logger log = LoggerFactory.getLogger(AdminApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

//    @org.springframework.beans.factory.annotation.Autowired
//    public AdminApiController(ObjectMapper objectMapper, HttpServletRequest request) {
//        this.objectMapper = objectMapper;
//        this.request = request;
//    }
    //Users {

    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody NewUserRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
//            try {
//                return new ResponseEntity<UserDto>(objectMapper.readValue("{\n  \"name\" : \"Петров Иван\",\n  \"id\" : 1,\n  \"email\" : \"petrov.i@practicummail.ru\"\n}", UserDto.class), HttpStatus.NOT_IMPLEMENTED);
//                return new ResponseEntity<UserDto>(userService.registerUser(body), HttpStatus.CREATED);
            return userService.registerUser(body);
//            } catch (IOException e) {
//                log.error("Couldn't serialize response for content type application/json", e);
//                return new ResponseEntity<UserDto>(HttpStatus.INTERNAL_SERVER_ERROR);
//            }
        }

        return new ResponseEntity<UserDto>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId) {
        String accept = request.getHeader("Accept");
        return userService.deleteUser(userId);
//        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<UserDto>> getUsers(@Valid @RequestParam(value = "ids", required = false) List<Long> ids, @Valid @RequestParam(value = "from", required = false, defaultValue = "0") Integer from, @Valid @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<UserDto>>(objectMapper.readValue("[ {\n  \"name\" : \"Петров Иван\",\n  \"id\" : 1,\n  \"email\" : \"petrov.i@practicummail.ru\"\n}, {\n  \"name\" : \"Петров Иван\",\n  \"id\" : 1,\n  \"email\" : \"petrov.i@practicummail.ru\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<UserDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<UserDto>>(HttpStatus.NOT_IMPLEMENTED);
    }

    //}Users


    //Categories {

    public ResponseEntity<Void> deleteCategory(@PathVariable("catId") Long catId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<CategoryDto> addCategory(@Valid @RequestBody NewCategoryDto body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<CategoryDto>(objectMapper.readValue("{\n  \"name\" : \"Концерты\",\n  \"id\" : 1\n}", CategoryDto.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<CategoryDto>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<CategoryDto>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<CategoryDto>(objectMapper.readValue("{\n  \"name\" : \"Концерты\",\n  \"id\" : 1\n}", CategoryDto.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<CategoryDto>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<CategoryDto>(HttpStatus.NOT_IMPLEMENTED);
    }

    //} Categories

    //Compilation {

    public ResponseEntity<Void> deleteCompilation(@PathVariable("compId") Long compId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> removeEventFromCompilation(@PathVariable("compId") Long compId, @PathVariable("eventId") Long eventId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<CompilationDto> saveCompilation(@Valid @RequestBody NewCompilationDto body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<CompilationDto>(objectMapper.readValue("{\n  \"pinned\" : true,\n  \"id\" : 1,\n  \"title\" : \"Летние концерты\",\n  \"events\" : [ {\n    \"annotation\" : \"Эксклюзивность нашего шоу гарантирует привлечение максимальной зрительской аудитории\",\n    \"category\" : {\n      \"id\" : 1,\n      \"name\" : \"Концерты\"\n    },\n    \"confirmedRequests\" : 5,\n    \"eventDate\" : \"2024-03-10 14:30:00\",\n    \"id\" : 1,\n    \"initiator\" : {\n      \"id\" : 3,\n      \"name\" : \"Фёдоров Матвей\"\n    },\n    \"paid\" : true,\n    \"title\" : \"Знаменитое шоу 'Летающая кукуруза'\",\n    \"views\" : 999\n  }, {\n    \"annotation\" : \"За почти три десятилетия группа 'Java Core' закрепились на сцене как группа, объединяющая поколения.\",\n    \"category\" : {\n      \"id\" : 1,\n      \"name\" : \"Концерты\"\n    },\n    \"confirmedRequests\" : 555,\n    \"eventDate\" : \"2025-09-13 21:00:00\",\n    \"id\" : 1,\n    \"initiator\" : {\n      \"id\" : 3,\n      \"name\" : \"Паша Петров\"\n    },\n    \"paid\" : true,\n    \"title\" : \"Концерт рок-группы 'Java Core'\",\n    \"views\" : 991\n  } ]\n}", CompilationDto.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<CompilationDto>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<CompilationDto>(HttpStatus.NOT_IMPLEMENTED);
    }


    //} Compilation

    //Events {

    public ResponseEntity<EventFullDto> rejectEvent(@PathVariable("eventId") Long eventId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<EventFullDto>(objectMapper.readValue("{\n  \"annotation\" : \"Эксклюзивность нашего шоу гарантирует привлечение максимальной зрительской аудитории\",\n  \"initiator\" : {\n    \"name\" : \"Фёдоров Матвей\",\n    \"id\" : 3\n  },\n  \"description\" : \"Что получится, если соединить кукурузу и полёт? Создатели \"Шоу летающей кукурузы\" испытали эту идею на практике и воплотили в жизнь инновационный проект, предлагающий свежий взгляд на развлечения...\",\n  \"publishedOn\" : \"2022-09-06 15:10:05\",\n  \"title\" : \"Знаменитое шоу 'Летающая кукуруза'\",\n  \"confirmedRequests\" : 5,\n  \"createdOn\" : \"2022-09-06 11:00:23\",\n  \"participantLimit\" : 10,\n  \"paid\" : true,\n  \"requestModeration\" : true,\n  \"location\" : {\n    \"lon\" : 37.62,\n    \"lat\" : 55.754167\n  },\n  \"id\" : 1,\n  \"state\" : \"PUBLISHED\",\n  \"category\" : {\n    \"name\" : \"Концерты\",\n    \"id\" : 1\n  },\n  \"views\" : 999,\n  \"eventDate\" : \"2024-12-31 15:10:05\"\n}", EventFullDto.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<EventFullDto>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<EventFullDto>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<EventFullDto> updateEvent(@PathVariable("eventId") Long eventId, @Valid @RequestBody AdminUpdateEventRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<EventFullDto>(objectMapper.readValue("{\n  \"annotation\" : \"Эксклюзивность нашего шоу гарантирует привлечение максимальной зрительской аудитории\",\n  \"initiator\" : {\n    \"name\" : \"Фёдоров Матвей\",\n    \"id\" : 3\n  },\n  \"description\" : \"Что получится, если соединить кукурузу и полёт? Создатели \"Шоу летающей кукурузы\" испытали эту идею на практике и воплотили в жизнь инновационный проект, предлагающий свежий взгляд на развлечения...\",\n  \"publishedOn\" : \"2022-09-06 15:10:05\",\n  \"title\" : \"Знаменитое шоу 'Летающая кукуруза'\",\n  \"confirmedRequests\" : 5,\n  \"createdOn\" : \"2022-09-06 11:00:23\",\n  \"participantLimit\" : 10,\n  \"paid\" : true,\n  \"requestModeration\" : true,\n  \"location\" : {\n    \"lon\" : 37.62,\n    \"lat\" : 55.754167\n  },\n  \"id\" : 1,\n  \"state\" : \"PUBLISHED\",\n  \"category\" : {\n    \"name\" : \"Концерты\",\n    \"id\" : 1\n  },\n  \"views\" : 999,\n  \"eventDate\" : \"2024-12-31 15:10:05\"\n}", EventFullDto.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<EventFullDto>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<EventFullDto>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> addEventToCompilation(@PathVariable("compId") Long compId, @PathVariable("eventId") Long eventId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<EventFullDto>> getEvents2(
            @Valid @RequestParam(value = "users", required = false) List<Long> users,
            @Valid @RequestParam(value = "states", required = false) List<String> states,
            @Valid @RequestParam(value = "categories", required = false) List<Long> categories,
            @Valid @RequestParam(value = "rangeStart", required = false) String rangeStart,
            @Valid @RequestParam(value = "rangeEnd", required = false) String rangeEnd,
            @Valid @RequestParam(value = "from", required = false, defaultValue = "0") Integer from, @Valid @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<EventFullDto>>(objectMapper.readValue("[ {\n  \"annotation\" : \"Эксклюзивность нашего шоу гарантирует привлечение максимальной зрительской аудитории\",\n  \"initiator\" : {\n    \"name\" : \"Фёдоров Матвей\",\n    \"id\" : 3\n  },\n  \"description\" : \"Что получится, если соединить кукурузу и полёт? Создатели \"Шоу летающей кукурузы\" испытали эту идею на практике и воплотили в жизнь инновационный проект, предлагающий свежий взгляд на развлечения...\",\n  \"publishedOn\" : \"2022-09-06 15:10:05\",\n  \"title\" : \"Знаменитое шоу 'Летающая кукуруза'\",\n  \"confirmedRequests\" : 5,\n  \"createdOn\" : \"2022-09-06 11:00:23\",\n  \"participantLimit\" : 10,\n  \"paid\" : true,\n  \"requestModeration\" : true,\n  \"location\" : {\n    \"lon\" : 37.62,\n    \"lat\" : 55.754167\n  },\n  \"id\" : 1,\n  \"state\" : \"PUBLISHED\",\n  \"category\" : {\n    \"name\" : \"Концерты\",\n    \"id\" : 1\n  },\n  \"views\" : 999,\n  \"eventDate\" : \"2024-12-31 15:10:05\"\n}, {\n  \"annotation\" : \"Эксклюзивность нашего шоу гарантирует привлечение максимальной зрительской аудитории\",\n  \"initiator\" : {\n    \"name\" : \"Фёдоров Матвей\",\n    \"id\" : 3\n  },\n  \"description\" : \"Что получится, если соединить кукурузу и полёт? Создатели \"Шоу летающей кукурузы\" испытали эту идею на практике и воплотили в жизнь инновационный проект, предлагающий свежий взгляд на развлечения...\",\n  \"publishedOn\" : \"2022-09-06 15:10:05\",\n  \"title\" : \"Знаменитое шоу 'Летающая кукуруза'\",\n  \"confirmedRequests\" : 5,\n  \"createdOn\" : \"2022-09-06 11:00:23\",\n  \"participantLimit\" : 10,\n  \"paid\" : true,\n  \"requestModeration\" : true,\n  \"location\" : {\n    \"lon\" : 37.62,\n    \"lat\" : 55.754167\n  },\n  \"id\" : 1,\n  \"state\" : \"PUBLISHED\",\n  \"category\" : {\n    \"name\" : \"Концерты\",\n    \"id\" : 1\n  },\n  \"views\" : 999,\n  \"eventDate\" : \"2024-12-31 15:10:05\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<EventFullDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<List<EventFullDto>>(HttpStatus.NOT_IMPLEMENTED);
    }

    //} Events

    public ResponseEntity<Void> pin(@PathVariable("compId") Long compId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<EventFullDto> publishEvent(@PathVariable("eventId") Long eventId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<EventFullDto>(objectMapper.readValue("{\n  \"annotation\" : \"Эксклюзивность нашего шоу гарантирует привлечение максимальной зрительской аудитории\",\n  \"initiator\" : {\n    \"name\" : \"Фёдоров Матвей\",\n    \"id\" : 3\n  },\n  \"description\" : \"Что получится, если соединить кукурузу и полёт? Создатели \"Шоу летающей кукурузы\" испытали эту идею на практике и воплотили в жизнь инновационный проект, предлагающий свежий взгляд на развлечения...\",\n  \"publishedOn\" : \"2022-09-06 15:10:05\",\n  \"title\" : \"Знаменитое шоу 'Летающая кукуруза'\",\n  \"confirmedRequests\" : 5,\n  \"createdOn\" : \"2022-09-06 11:00:23\",\n  \"participantLimit\" : 10,\n  \"paid\" : true,\n  \"requestModeration\" : true,\n  \"location\" : {\n    \"lon\" : 37.62,\n    \"lat\" : 55.754167\n  },\n  \"id\" : 1,\n  \"state\" : \"PUBLISHED\",\n  \"category\" : {\n    \"name\" : \"Концерты\",\n    \"id\" : 1\n  },\n  \"views\" : 999,\n  \"eventDate\" : \"2024-12-31 15:10:05\"\n}", EventFullDto.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<EventFullDto>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<EventFullDto>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> unpin(@PathVariable("compId") Long compId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
