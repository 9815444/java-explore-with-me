package ru.practicum.explorewithme.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.explorewithme.model.CompilationDto;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-11-13T14:31:14.091Z[GMT]")
@RestController
public class CompilationsApiController implements CompilationsApi {

    private static final Logger log = LoggerFactory.getLogger(CompilationsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public CompilationsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<CompilationDto> getCompilation(@PathVariable("compId") Long compId) {
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

    public ResponseEntity<List<CompilationDto>> getCompilations(@Valid @RequestParam(value = "pinned", required = false) Boolean pinned, @Valid @RequestParam(value = "from", required = false, defaultValue = "0") Integer from, @Valid @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<CompilationDto>>(objectMapper.readValue("[ {\n  \"pinned\" : true,\n  \"id\" : 1,\n  \"title\" : \"Летние концерты\",\n  \"events\" : [ {\n    \"annotation\" : \"Эксклюзивность нашего шоу гарантирует привлечение максимальной зрительской аудитории\",\n    \"category\" : {\n      \"id\" : 1,\n      \"name\" : \"Концерты\"\n    },\n    \"confirmedRequests\" : 5,\n    \"eventDate\" : \"2024-03-10 14:30:00\",\n    \"id\" : 1,\n    \"initiator\" : {\n      \"id\" : 3,\n      \"name\" : \"Фёдоров Матвей\"\n    },\n    \"paid\" : true,\n    \"title\" : \"Знаменитое шоу 'Летающая кукуруза'\",\n    \"views\" : 999\n  }, {\n    \"annotation\" : \"За почти три десятилетия группа 'Java Core' закрепились на сцене как группа, объединяющая поколения.\",\n    \"category\" : {\n      \"id\" : 1,\n      \"name\" : \"Концерты\"\n    },\n    \"confirmedRequests\" : 555,\n    \"eventDate\" : \"2025-09-13 21:00:00\",\n    \"id\" : 1,\n    \"initiator\" : {\n      \"id\" : 3,\n      \"name\" : \"Паша Петров\"\n    },\n    \"paid\" : true,\n    \"title\" : \"Концерт рок-группы 'Java Core'\",\n    \"views\" : 991\n  } ]\n}, {\n  \"pinned\" : true,\n  \"id\" : 1,\n  \"title\" : \"Летние концерты\",\n  \"events\" : [ {\n    \"annotation\" : \"Эксклюзивность нашего шоу гарантирует привлечение максимальной зрительской аудитории\",\n    \"category\" : {\n      \"id\" : 1,\n      \"name\" : \"Концерты\"\n    },\n    \"confirmedRequests\" : 5,\n    \"eventDate\" : \"2024-03-10 14:30:00\",\n    \"id\" : 1,\n    \"initiator\" : {\n      \"id\" : 3,\n      \"name\" : \"Фёдоров Матвей\"\n    },\n    \"paid\" : true,\n    \"title\" : \"Знаменитое шоу 'Летающая кукуруза'\",\n    \"views\" : 999\n  }, {\n    \"annotation\" : \"За почти три десятилетия группа 'Java Core' закрепились на сцене как группа, объединяющая поколения.\",\n    \"category\" : {\n      \"id\" : 1,\n      \"name\" : \"Концерты\"\n    },\n    \"confirmedRequests\" : 555,\n    \"eventDate\" : \"2025-09-13 21:00:00\",\n    \"id\" : 1,\n    \"initiator\" : {\n      \"id\" : 3,\n      \"name\" : \"Паша Петров\"\n    },\n    \"paid\" : true,\n    \"title\" : \"Концерт рок-группы 'Java Core'\",\n    \"views\" : 991\n  } ]\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<CompilationDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<CompilationDto>>(HttpStatus.NOT_IMPLEMENTED);
    }

}
