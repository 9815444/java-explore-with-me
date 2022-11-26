package ewm.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Validated
@Setter
@Getter
public class NewEventDto {
    @JsonProperty("annotation")
    @NotNull
    private String annotation = null;

    @JsonProperty("category")
    @NotNull
    private Long categoryId = null;

    @JsonProperty("description")
    @NotNull
    private String description = null;

    @JsonProperty("eventDate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull
    private LocalDateTime eventDate = null;

    @JsonProperty("location")
    @NotNull
    private Location location = null;

    @JsonProperty("paid")
    private Boolean paid = false;

    @JsonProperty("participantLimit")
    private Integer participantLimit = 0;

    @JsonProperty("requestModeration")
    private Boolean requestModeration = true;

    @JsonProperty("title")
    @NotNull
    private String title = null;

}
