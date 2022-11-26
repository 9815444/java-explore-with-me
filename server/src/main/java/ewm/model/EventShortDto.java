package ewm.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EventShortDto {
    @JsonProperty("annotation")
    private String annotation = null;

    @JsonProperty("category")
    private Category category = null;

    @JsonProperty("confirmedRequests")
    private Long confirmedRequests = null;

    @JsonProperty("eventDate")
    private String eventDate = null;

    @JsonProperty("id")
    private Long id = null;

    @JsonProperty("initiator")
    private UserShortDto initiator = null;

    @JsonProperty("paid")
    private Boolean paid = null;

    @JsonProperty("title")
    private String title = null;

    @JsonProperty("views")
    private Long views = null;

    public EventShortDto annotation(String annotation) {
        this.annotation = annotation;
        return this;
    }
}
