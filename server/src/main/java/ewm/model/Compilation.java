package ewm.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.List;

@Validated
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "compilation")
public class Compilation {

    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @JsonProperty("events")
    @Valid
    @Transient
    private List<Event> events = null;

    @JsonProperty("pinned")
    @Column(name = "pinned")
    private Boolean pinned = null;

    @JsonProperty("title")
    @Column(name = "title")
    private String title = null;

}
