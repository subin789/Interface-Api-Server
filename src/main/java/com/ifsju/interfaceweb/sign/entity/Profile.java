package com.ifsju.interfaceweb.sign.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Integer profileId;
    private String email;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "phone_number")
    private Integer phoneNumber;
    @Column(name = "student_id")
    private Integer studentId;
}
