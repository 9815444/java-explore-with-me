package ewm.repository;

import ewm.model.CompilationEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompilationEventRepository extends JpaRepository<CompilationEvent, Long> {

    List<CompilationEvent> findAllByCompilationId(Long id);

    List<CompilationEvent> findAllByCompilationIdAndEventId(Long comId, Long eventId);

}
