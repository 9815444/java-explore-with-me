package ewm.service;

import ewm.model.StatEntry;
import ewm.model.StatEntryDto;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsService {
    StatEntry add(StatEntry hit);

    List<StatEntryDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique);

}
