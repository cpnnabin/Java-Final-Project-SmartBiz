package com.meta.smartbiz.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data // This automatically creates getters, setters, toString, equals, hashCode
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private String role;
}