package ewm.service;

import ewm.errors.BadRequestException;
import ewm.errors.NotFoundException;
import ewm.mapper.UserMapper;
import ewm.model.NewUserDto;
import ewm.model.UserDto;
import ewm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto registerUser(NewUserDto newUserDto) {
        if (newUserDto.getName() == null
                || newUserDto.getName().isBlank()) {
            throw new BadRequestException("Не заполнено имя");
        }
        if (newUserDto.getEmail() == null
                || newUserDto.getEmail().isBlank()) {
            throw new BadRequestException("Не заполнено email");
        }
        var userDto = userRepository.save(UserMapper.newUserRequestToUserDto(newUserDto));
        return userDto;
    }

    @Override
    public void deleteUser(Long userId) {
        var user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Не найден пользователь"));
        userRepository.deleteById(userId);
    }

    @Override
    public List<UserDto> getUsers(List<Long> ids, Integer from, Integer size) {
        int fromPage = from.intValue() / size.intValue();
        Pageable pageable = PageRequest.of(fromPage, size.intValue());
        if (ids == null) {
            var users = userRepository.findAll(pageable).getContent();
            return users;
        } else {
            var users = userRepository.findAllByIdIn(ids, pageable).getContent();
            return users;
        }
    }
}
