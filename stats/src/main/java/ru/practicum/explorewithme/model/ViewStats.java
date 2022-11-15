package ru.practicum.explorewithme.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

@Validated
public class ViewStats {
    @JsonProperty("app")
    private String app = null;

    @JsonProperty("uri")
    private String uri = null;

    @JsonProperty("hits")
    private Long hits = null;

    public ViewStats app(String app) {
        this.app = app;
        return this;
    }

}
