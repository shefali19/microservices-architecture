package com.learning.user.services.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_roles")
public class User_Roles {

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name="userId", referencedColumnName = "userId", nullable = false)
    private User user;
    @Id
    private String id;
    private String user_role;
}