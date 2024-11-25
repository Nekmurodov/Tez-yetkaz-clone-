package com.example.Tez_Yetkaz.factory;



import com.example.Tez_Yetkaz.entity.user.Role;
import com.example.Tez_Yetkaz.entity.user.User;
import com.example.Tez_Yetkaz.enums.Permissions;

import java.util.List;

public class UserFactorySingleton {


    private static UserFactorySingleton instance;

    private UserFactorySingleton() {
    }

    public static synchronized UserFactorySingleton getInstance() {
        if (instance == null) {
            instance = new UserFactorySingleton();
        }
        return instance;
    }

    public User createUser(String firstName, String lastName, String phoneNumber, String password, String email, Role role, List<Permissions> permissionsSet) {
        return User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .phoneNumber(phoneNumber)
                .email(email)
                .password(password)
                .role(role)
                .permissions(permissionsSet)
                .enabled(true)
                .accountNonExpired(true)
                .credentialsNonExpired(true)
                .accountNonLocked(true)
                .build();
    }

}
