package com.example.Tez_Yetkaz.util;

import com.example.Tez_Yetkaz.entity.user.Role;
import com.example.Tez_Yetkaz.enums.Permissions;
import com.example.Tez_Yetkaz.enums.RoleType;
import com.example.Tez_Yetkaz.factory.UserFactorySingleton;
import com.example.Tez_Yetkaz.repository.RoleRepository;
import com.example.Tez_Yetkaz.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Value("${spring.sql.init.mode}")
    private String initMode;

    @Override
    public void run(String... args) throws Exception {
        if (initMode.equals("always")) {
            UserFactorySingleton instance = UserFactorySingleton.getInstance();


            Role superAdmin = new Role();
            superAdmin.setName("super-admin");
            superAdmin.setRoleType(RoleType.SUPER_ADMIN);
            roleRepository.save(superAdmin);


            Role user = new Role();
            user.setRoleType(RoleType.USER);
            user.setName("user");
            roleRepository.save(user);


            userRepository.save(instance.createUser(
                    "admin",
                    "Admin",
                    "123",
                    passwordEncoder.encode("123"),
                    "dilshodbeknekmurodov2@gmail.com",
                    superAdmin,
                    Arrays.stream(Permissions.values()).toList()));

            Role owner = new Role();
            superAdmin.setName("owner");
            superAdmin.setRoleType(RoleType.OWNER);
            roleRepository.save(owner);

            userRepository.save(instance.createUser(
                    "Owner",
                    "Owner",
                    "+998936588155",
                    passwordEncoder.encode("321"),
                    "dilshodbeknekmurodov@gmail.com",
                    owner,
                    Arrays.stream(Permissions.values()).toList()));

        }
    }
}
