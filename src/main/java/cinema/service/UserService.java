package cinema.service;

import cinema.model.User;
import java.util.Optional;
import org.springframework.security.core.Authentication;

public interface UserService {
    User add(User user);

    Optional<User> findByEmail(String email);

    Optional<User> findUser(Authentication authentication);
}
