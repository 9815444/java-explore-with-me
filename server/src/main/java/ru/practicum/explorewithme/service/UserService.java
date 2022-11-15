package ru.practicum.explorewithme.service;

import org.springframework.http.ResponseEntity;
import ru.practicum.explorewithme.model.NewUserRequest;
import ru.practicum.explorewithme.model.UserDto;

public interface UserService {

    ResponseEntity<UserDto> registerUser(NewUserRequest newUserRequest);

    ResponseEntity<Void> deleteUser(Long userId);
}
