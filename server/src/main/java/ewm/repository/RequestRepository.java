package ewm.repository;

import ewm.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    Optional<Request> findByEventAndRequester(Long event, Long userId);

    List<Request> findAllByEventAndStatus(Long event, Request.StateEnum status);

    List<Request> findAllByEvent(Long eventId);

    List<Request> findAllByRequester(Long userId);

}
