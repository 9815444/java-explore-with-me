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
import java.util.ArrayList;
import java.util.List;


@Validated
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "events")
public class Event {

    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    @JsonProperty("annotation")
    @Column(name = "annotation")
    private String annotation = null;

    @Column(name = "category_id", insertable = false, updatable = false)
    private Long categoryId;

    @Column(name = "location_id", insertable = false, updatable = false)
    private Long locationId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id")
    private Category category = null;

    @JsonProperty("confirmedRequests")
    @Column(name = "confirmed_requests")
    private Long confirmedRequests = null;

    @JsonProperty("createdOn")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @JsonProperty("description")
    @Column(name = "description")
    private String description = null;

    @JsonProperty("eventDate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "event_date")
    private LocalDateTime eventDate = null;

    @JsonProperty("initiator")
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User initiator = null;

    @JsonProperty("location")
    @ManyToOne(optional = false)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location = null;

    @Transient
    private List<Comment> comments = new ArrayList<>();

    @JsonProperty("paid")
    @Column(name = "paid")
    private Boolean paid = false;

    @JsonProperty("participantLimit")
    @Column(name = "participant_limit")
    private Integer participantLimit = 0;

    @JsonProperty("publishedOn")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "published_on")
    private LocalDateTime publishedOn;

    @JsonProperty("requestModeration")
    @Column(name = "request_moderation")
    private Boolean requestModeration = true;

    @JsonProperty("state")
    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private StateEnum state = null;

    @JsonProperty("title")
    @Column(name = "title")
    private String title = null;

    @JsonProperty("views")
    @Column(name = "views")
    private Long views = Long.valueOf(0);

    public enum StateEnum {
        PENDING,

        PUBLISHED,

        CANCELED;

    }

}
