package ewm.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Validated
@Setter
@Getter
public class UpdateEventRequest {
    @JsonProperty("annotation")
    private String annotation = null;

    @JsonProperty("category")
    private Long category = null;

    @JsonProperty("description")
    private String description = null;

    @JsonProperty("eventDate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate = null;

    @JsonProperty("eventId")
    private Long eventId = null;

    @JsonProperty("paid")
    private Boolean paid = null;

    @JsonProperty("participantLimit")
    private Integer participantLimit = null;

    @JsonProperty("title")
    private String title = null;

}
