package ewm.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ewm.model.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    Optional<Event> findByIdAndState(Long aLong, Event.StateEnum state);

    List<Event> findAllByUserId(Long userId, Pageable pageable);

    @Query(value =
            "SELECT u " +
            "FROM Event u" +
                    " WHERE " +
                    "((u.userId in (:users)) or (:allUsers = true))" +
                    "and" +
                    "((u.state in (:states)) or (:allStates = true))" +
                    "and" +
                    "((u.eventDate >= :rangeStart) or (:noStartDateRestrictions = true))" +
                    "and" +
                    "((u.eventDate <= :rangeEnd) or (:noEndDateRestrictions = true))")
    List<Event> getEvents(
            @Param("users") List<Long> users,
            @Param("allUsers") boolean allUsers,
            @Param("states") List<Event.StateEnum> states,
            @Param("allStates") boolean allStates,
            @Param("rangeStart") LocalDateTime rangeStart,
            @Param("noStartDateRestrictions") boolean noStartDateRestrictions,
            @Param("rangeEnd") LocalDateTime rangeEnd,
            @Param("noEndDateRestrictions") boolean noEndDateRestrictions,
            Pageable pageable);

    @Query(value =
            "SELECT u " +
                    "FROM Event u" +
                    " WHERE " +
                    "(u.description like CONCAT('%',:text,'%') or u.annotation like CONCAT('%',:text,'%')) " +
                    "and " +
                    "u.categoryId in :categories " +
                    "and " +
                    "u.paid = :paid " +
                    "and " +
                    "u.eventDate >= :rangeStart or :rangeStartisNull = true " +
                    "and " +
                    "u.eventDate <= :rangeEnd or :rangeEndIsNull = true " +
                    "and " +
                    "u.state = 'PUBLISHED' " +
                    "and " +
                    "((:rangeStartisNull = true and :rangeEndIsNull = true) " +
                    "or u.eventDate > :currentDateTime)"
    )
    List<Event> getAllEvents(
            @Param("text") String text,
            @Param("categories") List<Long> categories,
            @Param("paid") Boolean paid,
            @Param("rangeStart") LocalDateTime rangeStart,
            @Param("rangeStartisNull") boolean rangeStartIsNull,
            @Param("rangeEnd") LocalDateTime rangeEnd,
            @Param("rangeEndIsNull") boolean rangeEndIsNull,
            @Param("currentDateTime") LocalDateTime currentDateTime,
            Pageable pageable);

}
