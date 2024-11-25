package com.example.Tez_Yetkaz.entity.user;

import com.example.Tez_Yetkaz.entity.AbsEntity;
import com.example.Tez_Yetkaz.enums.Permissions;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity()
@Table(name = "users")
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends AbsEntity implements UserDetails {
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Date birthDate;
    private Boolean gender;


    @ManyToOne(optional = false)
    private Role role;

//    @OneToMany
//    private List<Notification> notifications;

    private boolean enabled = false;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean accountNonExpiredOrCredentialsNonExpired = true;


    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Permissions> permissions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
    }
}
