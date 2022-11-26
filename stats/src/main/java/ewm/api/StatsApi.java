package ewm.api;

import ewm.model.StatEntry;
import ewm.model.StatEntryDto;
import ewm.service.StatsService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StatsApi {

    private final StatsService statsService;

    private static final Logger log = LoggerFactory.getLogger(StatsApi.class);

    @RequestMapping(value = "/hit",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    public ResponseEntity<Void> add(@Valid @RequestBody StatEntry statEntry) {
        statsService.add(statEntry);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/stats",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<List<StatEntryDto>> stats(
            @Valid @RequestParam(value = "start", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
            @Valid @RequestParam(value = "end", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
            @Valid @RequestParam(value = "uris", required = false) List<String> uris,
            @Valid @RequestParam(value = "unique", required = false, defaultValue = "false") Boolean unique) {
//        return new ResponseEntity<>(statsService.getStats(start, end, uris, unique), HttpStatus.OK);
        var stats = statsService.getStats(start, end, uris, unique);
        return new ResponseEntity<>(stats, HttpStatus.OK);
    }

}

