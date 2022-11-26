package ewm.mapper;

import ewm.model.NewUserDto;
import ewm.model.User;

public class UserMapper {

    public static User newUserRequestToUserDto(NewUserDto newUserDto) {
        User user = new User();
        user.setName(newUserDto.getName());
        user.setEmail(newUserDto.getEmail());
        return user;
    }
}
