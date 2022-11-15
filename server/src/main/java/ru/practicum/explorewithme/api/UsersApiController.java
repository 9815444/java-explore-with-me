package ru.practicum.explorewithme.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.model.*;
import ru.practicum.explorewithme.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

//@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-11-13T14:31:14.091Z[GMT]")
@RestController
@RequiredArgsConstructor
@RequestMapping
public class UsersApiController implements UsersApi {

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final UserService userService;

//    @org.springframework.beans.factory.annotation.Autowired
//    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request, UserService userService) {
//        this.objectMapper = objectMapper;
//        this.request = request;
//        th
//    }

    public ResponseEntity<EventFullDto> addEvent(@PathVariable("userId") Long userId, @Valid @RequestBody NewEventDto body) {
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

    public ResponseEntity<ParticipationRequestDto> addParticipationRequest(@PathVariable("userId") Long userId, @NotNull @Valid @RequestParam(value = "eventId", required = true) Long eventId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ParticipationRequestDto>(objectMapper.readValue("{\n  \"requester\" : 2,\n  \"created\" : \"2022-09-06T21:10:05.432\",\n  \"id\" : 3,\n  \"event\" : 1,\n  \"status\" : \"PENDING\"\n}", ParticipationRequestDto.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ParticipationRequestDto>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ParticipationRequestDto>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<EventFullDto> cancelEvent(@PathVariable("userId") Long userId, @PathVariable("eventId") Long eventId) {
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

    public ResponseEntity<ParticipationRequestDto> cancelParticipationRequest(@PathVariable("userId") Long userId, @PathVariable("eventId") Long eventId, @PathVariable("reqId") Long reqId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ParticipationRequestDto>(objectMapper.readValue("{\n  \"requester\" : 2,\n  \"created\" : \"2022-09-06T21:10:05.432\",\n  \"id\" : 3,\n  \"event\" : 1,\n  \"status\" : \"PENDING\"\n}", ParticipationRequestDto.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ParticipationRequestDto>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ParticipationRequestDto>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ParticipationRequestDto> cancelRequest(@PathVariable("userId") Long userId, @PathVariable("requestId") Long requestId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ParticipationRequestDto>(objectMapper.readValue("{\n  \"requester\" : 2,\n  \"created\" : \"2022-09-06T21:10:05.432\",\n  \"id\" : 3,\n  \"event\" : 1,\n  \"status\" : \"PENDING\"\n}", ParticipationRequestDto.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ParticipationRequestDto>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ParticipationRequestDto>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ParticipationRequestDto> confirmParticipationRequest(@PathVariable("userId") Long userId, @PathVariable("eventId") Long eventId, @PathVariable("reqId") Long reqId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ParticipationRequestDto>(objectMapper.readValue("{\n  \"requester\" : 2,\n  \"created\" : \"2022-09-06T21:10:05.432\",\n  \"id\" : 3,\n  \"event\" : 1,\n  \"status\" : \"PENDING\"\n}", ParticipationRequestDto.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ParticipationRequestDto>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ParticipationRequestDto>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<EventFullDto> getEvent(@PathVariable("userId") Long userId, @PathVariable("eventId") Long eventId) {
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

    public ResponseEntity<List<ParticipationRequestDto>> getEventParticipants(@PathVariable("userId") Long userId, @PathVariable("eventId") Long eventId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<ParticipationRequestDto>>(objectMapper.readValue("[ {\n  \"requester\" : 2,\n  \"created\" : \"2022-09-06T21:10:05.432\",\n  \"id\" : 3,\n  \"event\" : 1,\n  \"status\" : \"PENDING\"\n}, {\n  \"requester\" : 2,\n  \"created\" : \"2022-09-06T21:10:05.432\",\n  \"id\" : 3,\n  \"event\" : 1,\n  \"status\" : \"PENDING\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<ParticipationRequestDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<ParticipationRequestDto>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<EventShortDto>> getEvents(@PathVariable("userId") Long userId, @Valid @RequestParam(value = "from", required = false, defaultValue = "0") Integer from, @Valid @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<EventShortDto>>(objectMapper.readValue("[ [ {\n  \"annotation\" : \"Эксклюзивность нашего шоу гарантирует привлечение максимальной зрительской аудитории\",\n  \"category\" : {\n    \"id\" : 1,\n    \"name\" : \"Концерты\"\n  },\n  \"confirmedRequests\" : 5,\n  \"eventDate\" : \"2024-03-10 14:30:00\",\n  \"id\" : 1,\n  \"initiator\" : {\n    \"id\" : 3,\n    \"name\" : \"Фёдоров Матвей\"\n  },\n  \"paid\" : true,\n  \"title\" : \"Знаменитое шоу 'Летающая кукуруза'\",\n  \"views\" : 999\n}, {\n  \"annotation\" : \"За почти три десятилетия группа 'Java Core' закрепились на сцене как группа, объединяющая поколения.\",\n  \"category\" : {\n    \"id\" : 1,\n    \"name\" : \"Концерты\"\n  },\n  \"confirmedRequests\" : 555,\n  \"eventDate\" : \"2025-09-13 21:00:00\",\n  \"id\" : 1,\n  \"initiator\" : {\n    \"id\" : 3,\n    \"name\" : \"Паша Петров\"\n  },\n  \"paid\" : true,\n  \"title\" : \"Концерт рок-группы 'Java Core'\",\n  \"views\" : 991\n} ], [ {\n  \"annotation\" : \"Эксклюзивность нашего шоу гарантирует привлечение максимальной зрительской аудитории\",\n  \"category\" : {\n    \"id\" : 1,\n    \"name\" : \"Концерты\"\n  },\n  \"confirmedRequests\" : 5,\n  \"eventDate\" : \"2024-03-10 14:30:00\",\n  \"id\" : 1,\n  \"initiator\" : {\n    \"id\" : 3,\n    \"name\" : \"Фёдоров Матвей\"\n  },\n  \"paid\" : true,\n  \"title\" : \"Знаменитое шоу 'Летающая кукуруза'\",\n  \"views\" : 999\n}, {\n  \"annotation\" : \"За почти три десятилетия группа 'Java Core' закрепились на сцене как группа, объединяющая поколения.\",\n  \"category\" : {\n    \"id\" : 1,\n    \"name\" : \"Концерты\"\n  },\n  \"confirmedRequests\" : 555,\n  \"eventDate\" : \"2025-09-13 21:00:00\",\n  \"id\" : 1,\n  \"initiator\" : {\n    \"id\" : 3,\n    \"name\" : \"Паша Петров\"\n  },\n  \"paid\" : true,\n  \"title\" : \"Концерт рок-группы 'Java Core'\",\n  \"views\" : 991\n} ] ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<EventShortDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<EventShortDto>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<ParticipationRequestDto>> getUserRequests(@PathVariable("userId") Long userId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<ParticipationRequestDto>>(objectMapper.readValue("[ {\n  \"requester\" : 2,\n  \"created\" : \"2022-09-06T21:10:05.432\",\n  \"id\" : 3,\n  \"event\" : 1,\n  \"status\" : \"PENDING\"\n}, {\n  \"requester\" : 2,\n  \"created\" : \"2022-09-06T21:10:05.432\",\n  \"id\" : 3,\n  \"event\" : 1,\n  \"status\" : \"PENDING\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<ParticipationRequestDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<ParticipationRequestDto>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<EventFullDto> updateEvent1(@PathVariable("userId") Long userId, @Valid @RequestBody UpdateEventRequest body) {
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

}
