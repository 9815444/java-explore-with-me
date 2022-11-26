package ewm.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Validated
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "stats")
public class StatEntry {

    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @JsonProperty("app")
    @Column(name = "app")
    @NotNull
    @NotBlank
    private String app = null;

    @JsonProperty("uri")
    @Column(name = "uri")
    @NotNull
    @NotBlank
    private String uri = null;

    @JsonProperty("ip")
    @Column(name = "ip")
    @NotNull
    @NotBlank
    private String ip = null;

    @JsonProperty("timestamp")
    @Column(name = "date_time")
    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp = null;

}
