package ewm.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
@Getter
public class NewUserDto {
    @JsonProperty("email")
    @NotNull
    private String email;

    @JsonProperty("name")
    @NotNull
    private String name;

}
