package ewm.repository;

import ewm.model.StatEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StatsRepository extends JpaRepository<StatEntry, Long> {

    //    @Query(value =
//            "SELECT u " +
//                    "FROM StatEntry u" +
//                    " WHERE " +
//                    "((u.uri in (:uris)) or (:allUris = true))" +
//                    "and" +
//                    "((u.state in (:states)) or (:allStates = true))" +
//                    "and" +
//                    "((u.timestamp >= :start) or (:noStartDateRestrictions = true))" +
//                    "and" +
//                    "((u.timestamp <= :end) or (:noEndDateRestrictions = true))")
//    List<StatEntry> getStats(
//            @Param("start") LocalDateTime start,
//            @Param("noStartDateRestrictions") boolean noStartDateRestrictions,
//            @Param("end") LocalDateTime End,
//            @Param("noEndDateRestrictions") boolean noEndDateRestrictions,
//            @Param("uris") List<String> uris,
//            @Param("allUris") boolean allUris,
//
//            @Param("states") List<Event.StateEnum> states,
//            @Param("allStates") boolean allStates,
//
//            Pageable pageable);
    @Query(value = "select s.app, s.uri, count(app) as hits " +
            "from stats s " +
            "where " +
            "(s.date_time >= ?1 or ?2 = true) " +
            "and " +
            "(s.date_time <= ?3 or ?4 = true) " +
            "and " +
            "(s.uri in ?5 or ?6 = true) " +
            "group by " +
            "app, uri"
            , nativeQuery = true)
    List<Object[]> getStats(LocalDateTime start,
                            boolean noStartDateRestrictions,
                            LocalDateTime End,
                            boolean noEndDateRestrictions,
                            List<String> uris,
                            boolean allUris);

    @Query(value = "select aui.app, uri, count(ip) as hits " +
            "from (select s.app as app, " +
            "             s.uri as uri, " +
            "             s.ip as ip " +
            "      from stats s " +
            "      where (s.date_time >= ?1 or ?2 = true) " +
            "        and (s.date_time <= ?3 or ?4 = true) " +
            "        and (s.uri in ?5 or ?6 = true) " +
            "      group by app, " +
            "               uri, " +
            "               ip) aui " +
            "group by aui.app, uri", nativeQuery = true)
    List<Object[]> getUniqIpStats(LocalDateTime start,
                                  boolean noStartDateRestrictions,
                                  LocalDateTime End,
                                  boolean noEndDateRestrictions,
                                  List<String> uris,
                                  boolean allUris);


}
