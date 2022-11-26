package ewm.service;

import ewm.model.NewUserDto;
import ewm.model.UserDto;

import java.util.List;

public interface UserService {

    UserDto registerUser(NewUserDto newUserDto);

    void deleteUser(Long userId);

    List<UserDto> getUsers(List<Long> ids, Integer from, Integer size);
}
