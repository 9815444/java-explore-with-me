package ewm.service;

import ewm.model.StatEntry;
import ewm.model.StatEntryDto;
import ewm.repository.StatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {

    private final StatsRepository statsRepository;

    @Override
    public StatEntry add(StatEntry statEntry) {
        return statsRepository.save(statEntry);
    }

    @Override
    public List<StatEntryDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
        if (uris == null) {
            uris = new ArrayList<>();
        }
        List<StatEntryDto> statEntryDtos = new ArrayList<>();
        List<Object[]> list;
        if (unique) {
            list = statsRepository.getUniqIpStats(
                    start,
                    start == null,
                    end,
                    end == null,
                    uris,
                    uris.size() == 0);
        } else {
            list = statsRepository.getStats(
                    start,
                    start == null,
                    end,
                    end == null,
                    uris,
                    uris.size() == 0);
        }

        for (Object[] objects : list) {
            var app = objects[0].toString();
            var uri = objects[1].toString();
            var hits = Long.valueOf(objects[2].toString());
            statEntryDtos.add(new StatEntryDto(app, uri, hits));
        }
        return statEntryDtos;
    }

}
