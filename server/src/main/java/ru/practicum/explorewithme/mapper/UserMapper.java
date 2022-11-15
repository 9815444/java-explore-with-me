package ru.practicum.explorewithme.mapper;

import ru.practicum.explorewithme.model.NewUserRequest;
import ru.practicum.explorewithme.model.UserDto;

public class UserMapper {

    public static UserDto newUserRequestToUserDto(NewUserRequest newUserRequest) {
        UserDto userDto = new UserDto();
        userDto.setName(newUserRequest.getName());
        userDto.setEmail(newUserRequest.getEmail());
        return userDto;
    }
}
