package ewm.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Objects;

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
