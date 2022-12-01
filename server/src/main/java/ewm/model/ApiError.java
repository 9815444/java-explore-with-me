package ewm.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Validated
@Setter
public class ApiError {

    @JsonProperty("errors")
    @Valid
    private List<String> errors = new ArrayList<>();

    @JsonProperty("message")
    private String message = null;

    @JsonProperty("reason")
    private String reason = "";

    @JsonProperty("status")
    private Status status = null;

    @JsonProperty("timestamp")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp = null;

    public enum Status {
        FORBIDDEN,
        NOT_FOUND,
        CONFLICT,
        INTERNAL_SERVER_ERROR
    }
}
