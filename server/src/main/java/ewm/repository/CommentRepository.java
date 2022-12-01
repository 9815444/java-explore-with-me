package ewm.repository;

import ewm.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByEventIdAndPublishedIsTrue(Long eventId);

    List<Comment> findAllByUserIdAndEventId(Long userId, Long eventId);

    List<Comment> findAllByUserId(Long userId);
}
