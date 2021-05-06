package com.uter.entities;

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
@NamedQuery(name = "Customer.findByUsername",query =
        "select c from Customer c where c.username= ?1")
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

    @Column(name = "gender",nullable = false,length = 100)
    private String gender;
}
