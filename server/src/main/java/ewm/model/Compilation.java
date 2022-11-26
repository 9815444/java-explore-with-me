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

//    public Compilation events(List<EventShortDto> events) {
//        this.events = events;
//        return this;
//    }
//
//    public Compilation addEventsItem(EventShortDto eventsItem) {
//        if (this.events == null) {
//            this.events = new ArrayList<EventShortDto>();
//        }
//        this.events.add(eventsItem);
//        return this;
//    }
//
//    /**
//     * Список событий входящих в подборку
//     *
//     * @return events
//     **/
//    @Schema(example = "[{\"annotation\":\"Эксклюзивность нашего шоу гарантирует привлечение максимальной зрительской аудитории\",\"category\":{\"id\":1,\"name\":\"Концерты\"},\"confirmedRequests\":5,\"eventDate\":\"2024-03-10 14:30:00\",\"id\":1,\"initiator\":{\"id\":3,\"name\":\"Фёдоров Матвей\"},\"paid\":true,\"title\":\"Знаменитое шоу 'Летающая кукуруза'\",\"views\":999},{\"annotation\":\"За почти три десятилетия группа 'Java Core' закрепились на сцене как группа, объединяющая поколения.\",\"category\":{\"id\":1,\"name\":\"Концерты\"},\"confirmedRequests\":555,\"eventDate\":\"2025-09-13 21:00:00\",\"id\":1,\"initiator\":{\"id\":3,\"name\":\"Паша Петров\"},\"paid\":true,\"title\":\"Концерт рок-группы 'Java Core'\",\"views\":991}]", description = "Список событий входящих в подборку")
//    @Valid
//    public List<EventShortDto> getEvents() {
//        return events;
//    }
//
//    public void setEvents(List<EventShortDto> events) {
//        this.events = events;
//    }
//
//    public Compilation id(Long id) {
//        this.id = id;
//        return this;
//    }
//
//    /**
//     * Идентификатор
//     *
//     * @return id
//     **/
//    @Schema(example = "1", required = true, description = "Идентификатор")
//    @NotNull
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Compilation pinned(Boolean pinned) {
//        this.pinned = pinned;
//        return this;
//    }
//
//    /**
//     * Закреплена ли подборка на главной странице сайта
//     *
//     * @return pinned
//     **/
//    @Schema(example = "true", required = true, description = "Закреплена ли подборка на главной странице сайта")
//    @NotNull
//
//    public Boolean isPinned() {
//        return pinned;
//    }
//
//    public void setPinned(Boolean pinned) {
//        this.pinned = pinned;
//    }
//
//    public Compilation title(String title) {
//        this.title = title;
//        return this;
//    }
//
//    /**
//     * Заголовок подборки
//     *
//     * @return title
//     **/
//    @Schema(example = "Летние концерты", required = true, description = "Заголовок подборки")
//    @NotNull
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
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
//        Compilation compilation = (Compilation) o;
//        return Objects.equals(this.events, compilation.events) &&
//                Objects.equals(this.id, compilation.id) &&
//                Objects.equals(this.pinned, compilation.pinned) &&
//                Objects.equals(this.title, compilation.title);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(events, id, pinned, title);
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("class CompilationDto {\n");
//
//        sb.append("    events: ").append(toIndentedString(events)).append("\n");
//        sb.append("    id: ").append(toIndentedString(id)).append("\n");
//        sb.append("    pinned: ").append(toIndentedString(pinned)).append("\n");
//        sb.append("    title: ").append(toIndentedString(title)).append("\n");
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
