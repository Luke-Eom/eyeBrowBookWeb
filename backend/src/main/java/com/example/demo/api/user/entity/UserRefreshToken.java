package com.example.demo.api.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserRefreshToken {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long refreshTokenSeq;

    @Column(length = 512, unique = true)
    @NotNull
    private String email;

    @Column(length =256)
    @NotNull
    private String refreshToken;

    public UserRefreshToken(@NotNull String email, @NotNull String refreshToken) {
        this.email = email;
        this.refreshToken = refreshToken;
    }

}
