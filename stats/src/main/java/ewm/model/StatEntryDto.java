package ewm.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@Setter
@AllArgsConstructor
public class StatEntryDto {
    @JsonProperty("app")
    private String app = null;

    @JsonProperty("uri")
    private String uri = null;

    @JsonProperty("hits")
    private Long hits = null;

}
