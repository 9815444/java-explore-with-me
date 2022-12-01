package ewm.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.time.LocalDateTime;

@Validated
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "requests")
public class Request {

    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @JsonProperty("created")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created")
    private LocalDateTime created = null;

    @JsonProperty("event")
    @Column(name = "event")
    private Long event = null;

    @JsonProperty("requester")
    @Column(name = "requester")
    private Long requester = null;

    @JsonProperty("status")
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StateEnum status = null;

    public enum StateEnum {

        PENDING,

        CONFIRMED,

        REJECTED,

        CANCELED

    }

}
