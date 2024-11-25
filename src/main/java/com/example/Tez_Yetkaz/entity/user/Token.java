package com.example.Tez_Yetkaz.entity.user;

import com.example.Tez_Yetkaz.entity.AbsEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
public class Token extends AbsEntity {

    @Column(unique = true)
    String token;

    String tokenType;

    boolean revoked;

    boolean expired;

    @ManyToOne()
    User user;
}
