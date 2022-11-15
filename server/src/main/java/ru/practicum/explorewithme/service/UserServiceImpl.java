package ru.practicum.explorewithme.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.mapper.UserMapper;
import ru.practicum.explorewithme.model.NewUserRequest;
import ru.practicum.explorewithme.model.UserDto;
import ru.practicum.explorewithme.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<UserDto> registerUser(NewUserRequest newUserRequest) {
        try {
            var userDto = userRepository.save(UserMapper.newUserRequestToUserDto(newUserRequest));
            return new ResponseEntity<>(userDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<UserDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long userId) {
        try {
            userRepository.deleteById(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
