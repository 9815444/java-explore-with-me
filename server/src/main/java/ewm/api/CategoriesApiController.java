package ewm.api;

import ewm.model.Category;
import ewm.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CategoriesApiController {

    private final CategoryService categoryService;

    @RequestMapping(value = "/categories",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<List<Category>> getCategories(
            @Valid @RequestParam(value = "from", required = false, defaultValue = "0") Integer from,
            @Valid @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        return new ResponseEntity<>(categoryService.getCategories(from, size), HttpStatus.OK);
    }

    @RequestMapping(value = "/categories/{catId}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<Category> getCategory(@PathVariable("catId") Long catId) {
        var categoryDto = categoryService.getCategory(catId);
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }

}
