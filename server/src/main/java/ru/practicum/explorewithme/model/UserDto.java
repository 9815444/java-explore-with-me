package ru.practicum.explorewithme.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.util.Objects;

@Validated
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class UserDto {

    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @JsonProperty("name")
    @Column(name = "name")
    private String name = null;

    @JsonProperty("email")
    @Column(name = "email")
    private String email = null;

    public UserDto email(String email) {
        this.email = email;
        return this;
    }

    public UserDto id(Long id) {
        this.id = id;
        return this;
    }

    public UserDto name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserDto userDto = (UserDto) o;
        return Objects.equals(this.email, userDto.email) &&
                Objects.equals(this.id, userDto.id) &&
                Objects.equals(this.name, userDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, id, name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class UserDto {\n");

        sb.append("    email: ").append(toIndentedString(email)).append("\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
