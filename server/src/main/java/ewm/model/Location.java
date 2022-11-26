package ewm.model;

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
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @JsonProperty("lat")
    private Float lat = null;

    @JsonProperty("lon")
    private Float lon = null;

//    public Location lat(Float lat) {
//        this.lat = lat;
//        return this;
//    }
//
//    /**
//     * Широта
//     *
//     * @return lat
//     **/
//    @Schema(example = "55.754167", description = "Широта")
//
//    public Float getLat() {
//        return lat;
//    }
//
//    public void setLat(Float lat) {
//        this.lat = lat;
//    }
//
//    public Location lon(Float lon) {
//        this.lon = lon;
//        return this;
//    }
//
//    /**
//     * Долгота
//     *
//     * @return lon
//     **/
//    @Schema(example = "37.62", description = "Долгота")
//
//    public Float getLon() {
//        return lon;
//    }
//
//    public void setLon(Float lon) {
//        this.lon = lon;
//    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Location location = (Location) o;
        return Objects.equals(this.lat, location.lat) &&
                Objects.equals(this.lon, location.lon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lat, lon);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Location {\n");

        sb.append("    lat: ").append(toIndentedString(lat)).append("\n");
        sb.append("    lon: ").append(toIndentedString(lon)).append("\n");
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