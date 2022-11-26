package ewm.api;

import ewm.model.Compilation;
import ewm.service.EventService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CompilationsApiController {

    private static final Logger log = LoggerFactory.getLogger(CompilationsApiController.class);

    private final EventService eventService;

    @RequestMapping(value = "/compilations/{compId}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<Compilation> getCompilation(@PathVariable("compId") Long compId) {
        return new ResponseEntity<Compilation>(eventService.getCompilation(compId), HttpStatus.OK);
    }

    @RequestMapping(value = "/compilations",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<List<Compilation>> getCompilations(
            @Valid @RequestParam(value = "pinned", required = false) Boolean pinned,
            @Valid @RequestParam(value = "from", required = false, defaultValue = "0") Integer from,
            @Valid @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        return new ResponseEntity<List<Compilation>>(eventService.getCompilations(pinned, from, size), HttpStatus.OK);
    }

}
