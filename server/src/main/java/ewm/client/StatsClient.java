package ewm.client;

import ewm.model.StatEntry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;


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

//    public ResponseEntity<Object> updateUser(Long userId, UserDto userDto) {
//        return patch("/" + userId, userDto);
//    }
//
//    public void deleteUser(Long userId) {
//        delete("/" + userId);
//    }
//
//    public ResponseEntity<Object> getUser(Long userId) {
//        return get("/" + userId);
//    }
//
//    public ResponseEntity<Object> getAllUsers() {
//        return get("/");
//    }

}
