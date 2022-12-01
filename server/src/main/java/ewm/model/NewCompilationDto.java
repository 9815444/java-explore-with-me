package ewm.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class NewCompilationDto {

    @JsonProperty("events")
    @Valid
    private List<Long> events;

    @JsonProperty("pinned")
    @NotNull
    private Boolean pinned = false;

    @JsonProperty("title")
    @Valid
    @NotNull
    @NotBlank
    private String title;

}
