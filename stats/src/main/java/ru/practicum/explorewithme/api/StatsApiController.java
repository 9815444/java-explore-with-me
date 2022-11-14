package ru.practicum.explorewithme.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.explorewithme.model.ViewStats;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-11-14T14:41:24.731Z[GMT]")
@RestController
public class StatsApiController implements StatsApi {

    private static final Logger log = LoggerFactory.getLogger(StatsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public StatsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<ViewStats>> getStats(@NotNull @Parameter(in = ParameterIn.QUERY, description = "Дата и время начала диапазона за который нужно выгрузить статистику (в формате \"yyyy-MM-dd HH:mm:ss\")" ,required=true,schema=@Schema()) @Valid @RequestParam(value = "start", required = true) String start, @NotNull @Parameter(in = ParameterIn.QUERY, description = "Дата и время конца диапазона за который нужно выгрузить статистику (в формате \"yyyy-MM-dd HH:mm:ss\")" ,required=true,schema=@Schema()) @Valid @RequestParam(value = "end", required = true) String end, @Parameter(in = ParameterIn.QUERY, description = "Список uri для которых нужно выгрузить статистику" ,schema=@Schema()) @Valid @RequestParam(value = "uris", required = false) List<String> uris, @Parameter(in = ParameterIn.QUERY, description = "Нужно ли учитывать только уникальные посещения (только с уникальным ip)" ,schema=@Schema( defaultValue="false")) @Valid @RequestParam(value = "unique", required = false, defaultValue="false") Boolean unique) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<ViewStats>>(objectMapper.readValue("[ {\n  \"app\" : \"app\",\n  \"hits\" : 0,\n  \"uri\" : \"uri\"\n}, {\n  \"app\" : \"app\",\n  \"hits\" : 0,\n  \"uri\" : \"uri\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<ViewStats>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<ViewStats>>(HttpStatus.NOT_IMPLEMENTED);
    }

}