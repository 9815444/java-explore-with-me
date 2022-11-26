package ewm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ewm.model.UserDto;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserDto, Long> {

    Page<UserDto> findAllByIdIn(List<Long> ids, Pageable pageable);

}