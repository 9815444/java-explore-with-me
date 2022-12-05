package ewm.api;

import ewm.model.StatEntry;
import ewm.model.StatEntryDto;
import ewm.service.StatsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Request;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class StatsApi {

    private final StatsService statsService;

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
        var stats = statsService.getStats(start, end, uris, unique);
        return new ResponseEntity<>(stats, HttpStatus.OK);
    }

    @RequestMapping(value = "/stats/hits",
            method = RequestMethod.GET)
    public ResponseEntity<List<StatEntryDto>> eventsHits(
            @Valid @RequestParam(value = "uris", required = false) List<String> uris, Request request) {
        uris = uris.stream().map((s) -> s.replace("[", "").replace("]", "")).collect(Collectors.toList());
        var stats = statsService.getStats(null, null, uris, false);
        return new ResponseEntity<>(stats, HttpStatus.OK);
    }

}

