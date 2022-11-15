package ru.practicum.explorewithme.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.explorewithme.model.CategoryDto;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
public class CategoriesApiController implements CategoriesApi {

    private static final Logger log = LoggerFactory.getLogger(CategoriesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public CategoriesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<CategoryDto>> getCategories(
            @Valid @RequestParam(value = "from", required = false, defaultValue = "0") Integer from, @Valid @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<CategoryDto>>(objectMapper.readValue("[ {\n  \"name\" : \"Концерты\",\n  \"id\" : 1\n}, {\n  \"name\" : \"Концерты\",\n  \"id\" : 1\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<CategoryDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<CategoryDto>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<CategoryDto> getCategory(@PathVariable("catId") Long catId) {
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

}
