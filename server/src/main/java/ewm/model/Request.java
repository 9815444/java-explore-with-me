package ewm.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
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
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
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

        PENDING("PENDING"),

        CONFIRMED("CONFIRMED"),

        REJECTED("REJECTED"),

        CANCELED("CANCELED");

        private String value;

        StateEnum(String value) {
            this.value = value;
        }

        @Override
        @JsonValue
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static Request.StateEnum fromValue(String text) {
            for (Request.StateEnum b : Request.StateEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }
    }

//    public ParticipationRequestDto created(String created) {
//        this.created = created;
//        return this;
//    }
//
//    /**
//     * Дата и время создания заявки
//     *
//     * @return created
//     **/
//    @Schema(example = "2022-09-06T21:10:05.432", description = "Дата и время создания заявки")
//
//    public String getCreated() {
//        return created;
//    }
//
//    public void setCreated(String created) {
//        this.created = created;
//    }
//
//    public ParticipationRequestDto event(Long event) {
//        this.event = event;
//        return this;
//    }
//
//    /**
//     * Идентификатор события
//     *
//     * @return event
//     **/
//    @Schema(example = "1", description = "Идентификатор события")
//
//    public Long getEvent() {
//        return event;
//    }
//
//    public void setEvent(Long event) {
//        this.event = event;
//    }
//
//    public ParticipationRequestDto id(Long id) {
//        this.id = id;
//        return this;
//    }
//
//    /**
//     * Идентификатор заявки
//     *
//     * @return id
//     **/
//    @Schema(example = "3", description = "Идентификатор заявки")
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public ParticipationRequestDto requester(Long requester) {
//        this.requester = requester;
//        return this;
//    }
//
//    /**
//     * Идентификатор пользователя, отправившего заявку
//     *
//     * @return requester
//     **/
//    @Schema(example = "2", description = "Идентификатор пользователя, отправившего заявку")
//
//    public Long getRequester() {
//        return requester;
//    }
//
//    public void setRequester(Long requester) {
//        this.requester = requester;
//    }
//
//    public ParticipationRequestDto status(String status) {
//        this.status = status;
//        return this;
//    }
//
//    /**
//     * Статус заявки
//     *
//     * @return status
//     **/
//    @Schema(example = "PENDING", description = "Статус заявки")
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//
//    @Override
//    public boolean equals(java.lang.Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (o == null || getClass() != o.getClass()) {
//            return false;
//        }
//        ParticipationRequestDto participationRequestDto = (ParticipationRequestDto) o;
//        return Objects.equals(this.created, participationRequestDto.created) &&
//                Objects.equals(this.event, participationRequestDto.event) &&
//                Objects.equals(this.id, participationRequestDto.id) &&
//                Objects.equals(this.requester, participationRequestDto.requester) &&
//                Objects.equals(this.status, participationRequestDto.status);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(created, event, id, requester, status);
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("class ParticipationRequestDto {\n");
//
//        sb.append("    created: ").append(toIndentedString(created)).append("\n");
//        sb.append("    event: ").append(toIndentedString(event)).append("\n");
//        sb.append("    id: ").append(toIndentedString(id)).append("\n");
//        sb.append("    requester: ").append(toIndentedString(requester)).append("\n");
//        sb.append("    status: ").append(toIndentedString(status)).append("\n");
//        sb.append("}");
//        return sb.toString();
//    }
//
//    /**
//     * Convert the given object to string with each line indented by 4 spaces
//     * (except the first line).
//     */
//    private String toIndentedString(java.lang.Object o) {
//        if (o == null) {
//            return "null";
//        }
//        return o.toString().replace("\n", "\n    ");
//    }
}