package com.example.demo.api.user.entity;

import com.example.demo.api.user.dto.JoinDto;
import com.example.demo.api.user.dto.UserDto;
import com.example.demo.oauth.entity.ProviderType;
import com.example.demo.oauth.entity.RoleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "User")
public class MyUser {

    @JsonIgnore
    @Id
    @Column(name = "USER_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userSeq;

    @Column(name = "EMAIL", length = 512, unique = true)
    @NotNull
    private String email;

    @JsonIgnore
    @Column(name = "PASSWORD", length = 128)
    @NotNull
    private String password;

    @Column(name = "USERNAME", length = 20)
    @NotNull
    private String username;

    @Column(name = "PROFILE_IMAGE_URL", length = 512)
    @NotNull
    private String profileImgUrl;

    @Column(name = "PROVIDER_TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    @NotNull
    private ProviderType providerType;

    @Column(name = "ROLE_TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    @NotNull
    private RoleType roleType;

    @Column(name = "EMAIL_VERIFIED_YN", length = 1)
    @NotNull
    private String emailVerifiedYn;

    @Column(name = "CREATED_AT")
    @NotNull
    private LocalDateTime createdAt;

    @Column(name = "MODIFIED_AT")
    @NotNull
    private LocalDateTime modifiedAt;

    public MyUser(
            @NotNull String email,
            @NotNull String username,
            @NotNull String profileImageUrl,
            @NotNull ProviderType providerType,
            @NotNull RoleType roleType,
            @NotNull String emailVerifiedYn,
            @NotNull LocalDateTime createdAt,
            @NotNull LocalDateTime modifiedAt
    ) {
        this.email = email != null ? email : "이메일이 없습니다";
        this.password = "NO_PASS";
        this.username = username;
        this.profileImgUrl = profileImageUrl != null ? profileImgUrl : "";
        this.providerType = providerType;
        this.roleType = roleType;
        this.emailVerifiedYn = emailVerifiedYn;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public UserDto toDTO() {
        return new UserDto(email,username,profileImgUrl,providerType,roleType.getCode());
    }

    public MyUser(JoinDto joinDto, LocalDateTime createdAt, LocalDateTime modifiedAt, String imageUrl) {
        this.email = joinDto.getEmail();
        this.password = joinDto.getPassword();
        this.username = joinDto.getName();
        this.profileImgUrl = imageUrl;
        this.providerType = ProviderType.LOCAL;
        this.roleType = RoleType.USER;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
