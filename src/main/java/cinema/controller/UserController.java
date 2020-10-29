package cinema.controller;

import cinema.model.dto.user.UserDtoMapper;
import cinema.model.dto.user.UserResponseDto;
import cinema.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    public UserController(UserService userService, UserDtoMapper userDtoMapper) {
        this.userService = userService;
        this.userDtoMapper = userDtoMapper;
    }

    @GetMapping("/by-email")
    public UserResponseDto getByEmail(Authentication user) {
        return userDtoMapper.mapToDto(userService.findUser(user).get());
    }
}
