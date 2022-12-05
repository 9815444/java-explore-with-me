package ewm.client;

import ewm.model.Event;
import ewm.model.StatEntry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class StatsClient extends BaseClient {

    public StatsClient(@Value("${stats-app.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }

    public ResponseEntity<Object> addStatEntry(StatEntry statEntry) {
        return post("/hit", statEntry);
    }

    public ResponseEntity<Object> getEventHits(List<Event> events) {
        var uris = events.stream().map(event -> "/events/" + event.getId().toString()).collect(Collectors.toList());
        var params = new HashMap<String, Object>();
        params.put("uris", uris);
        return get("/stats/hits?uris={uris}", null, params);
    }

}
