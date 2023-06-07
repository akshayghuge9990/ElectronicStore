package com.electronicstore.entity;


import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User  {
    @Id
    private String userId;
    @Column(name = "USER_NAME")
    private String name;
    @Column(name = "USER_PASSWORD")
    private String password;
    @Column(name = "USER_GENDER")
    private String gender;
    @Column(name = "USER_ABOUT")
    private String about;
    @Column(name = "USER_IMAGENAME")
    private String imageName;
    @Column(name = "USER_EMAIL", nullable = false)
    private String email;


}
