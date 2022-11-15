package ru.practicum.explorewithme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.explorewithme.model.UserDto;

@Repository
public interface UserRepository extends JpaRepository<UserDto, Long> {
}
