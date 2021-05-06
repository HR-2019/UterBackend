package com.uter.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="seller")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seller implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name", nullable = false, length = 50)
    private String name;
    @Column(name="username", nullable = false, length = 50)
    private String username;
    @Column(name="password", nullable = false, length = 20)
    private String password;
    @Column(name="email", nullable = false, length = 50)
    private String correo;
    @Column(name="phone", nullable = false, length = 9) // podría ser nullable (revisar)
    private int phone;
    @Column(name="gender", nullable = false, length = 2)
    private String gender;

}
