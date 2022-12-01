package ewm.service;

import ewm.model.Location;

import java.util.Optional;

public interface LocationService {

    Location addLocation(Location location);

    Optional<Location> findLocation(Float lat, Float lon);

}
