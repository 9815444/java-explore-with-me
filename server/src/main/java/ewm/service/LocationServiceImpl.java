package ewm.service;

import ewm.model.Location;
import ewm.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    @Override
    public Location addLocation(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public Optional<Location> findLocation(Float lat, Float lon) {
        return locationRepository.findLocationByLatAndLon(lat, lon);
    }
}
