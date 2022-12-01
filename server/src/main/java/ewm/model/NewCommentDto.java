package ewm.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Validated
@Getter
@Setter
@RequiredArgsConstructor
public class NewCommentDto {

    @NotNull
    @NotBlank
    @Valid
    private String text;

}
