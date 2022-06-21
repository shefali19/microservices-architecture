package com.learning.user.services.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId")
    private long userId;
    @Column(name = "username")
    private String username;
    @Column(name = "emailaddress")
    private String emailaddress;
    @Column(name = "password")
    private String password;
    @Column(name = "departmentId")
    private String departmentId;
    @OneToMany(mappedBy="user",fetch=FetchType.LAZY,cascade = CascadeType.PERSIST)
    private List<User_Roles> roles = new ArrayList<>();

}
