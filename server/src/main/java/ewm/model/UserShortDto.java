package ewm.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@Setter
public class UserShortDto {
    @JsonProperty("id")
    private Long id = null;

    @JsonProperty("name")
    private String name = null;

}
