package ewm.mapper;

import ewm.model.NewUserDto;
import ewm.model.UserDto;

public class UserMapper {

    public static UserDto newUserRequestToUserDto(NewUserDto newUserDto) {
        UserDto userDto = new UserDto();
        userDto.setName(newUserDto.getName());
        userDto.setEmail(newUserDto.getEmail());
        return userDto;
    }
}
