package com.uter.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name="name",nullable = false, length = 50)
    private String name;

    @Column(name="password",nullable = false,length = 15)
    private String password;


    @Column(name = "username",nullable = false,length = 50)
    private String username;

    @Column(name="email",nullable = true,length = 50)
    private String email;

    @Column(name = "phone",nullable = true,length = 9)
    private String phone;

    @Column(name = "gender",nullable = false,length = 2)
    private String gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @JoinColumn(name="rol_id",nullable = false)
    private Rol rol;

}