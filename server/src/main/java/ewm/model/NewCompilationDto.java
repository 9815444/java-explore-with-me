package ewm.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Valid
public class NewCompilationDto {

    @JsonProperty("events")
    @Valid
    private List<Long> events = null;

    @JsonProperty("pinned")
    @NotNull
    private Boolean pinned = false;

    @JsonProperty("title")
    @NotNull
    @NotBlank
    private String title = null;

}
