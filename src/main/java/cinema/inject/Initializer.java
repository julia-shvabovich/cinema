package cinema.inject;

import cinema.model.Role;
import cinema.model.User;
import cinema.service.RoleService;
import cinema.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class Initializer {
    private final RoleService roleService;
    private final UserService userService;

    public Initializer(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    private void inject() {
        Role adminRole = Role.of("ADMIN");
        Role userRole = Role.of("USER");
        roleService.add(adminRole);
        roleService.add(userRole);

        User admin = new User();
        admin.setEmail("admin@gmail.com");
        admin.setPassword("12345");
        admin.setRoles(Set.of(roleService.getRoleByName("ADMIN")));
        userService.add(admin);

        User user = new User();
        user.setEmail("secretmail@gmail.com");
        user.setPassword("54321");
        user.setRoles(Set.of(roleService.getRoleByName("USER")));
        userService.add(user);
    }
}
