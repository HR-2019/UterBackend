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
    @Column(name="NName", nullable = false, length = 50)
    private String name;
    @Column(name="NUsername", nullable = false, length = 50)
    private String username;
    @Column(name="NPassword", nullable = false, length = 20)
    private String password;
    @Column(name="NCorreo", nullable = false, length = 50)
    private String correo;
    @Column(name="NPhone", nullable = false, length = 9) // podría ser nullable (revisar)
    private int phone;
    @Column(name="FGender", nullable = false, length = 2)
    private String gender;

}
