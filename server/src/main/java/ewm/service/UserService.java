package ewm.service;

import ewm.model.NewUserDto;
import ewm.model.User;

import java.util.List;

public interface UserService {

    User registerUser(NewUserDto newUserDto);

    void deleteUser(Long userId);

    List<User> getUsers(List<Long> ids, Integer from, Integer size);
}
