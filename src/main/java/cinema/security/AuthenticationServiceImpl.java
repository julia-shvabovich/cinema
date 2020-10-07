package cinema.security;

import cinema.exception.AuthenticationException;
import cinema.lib.Inject;
import cinema.lib.Service;
import cinema.model.User;
import cinema.service.UserService;
import cinema.util.HashUtil;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        Optional<User> user = userService.findByEmail(email);
        if (user.isEmpty() || !isValid(password, user.get())) {
            throw new AuthenticationException("Incorrect username or password");
        }
        return user.get();
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        return userService.add(user);
    }

    private boolean isValid(String password, User user) {
        byte[] salt = user.getSalt();
        return HashUtil.hashPassword(password, salt).equals(user.getPassword());
    }
}
