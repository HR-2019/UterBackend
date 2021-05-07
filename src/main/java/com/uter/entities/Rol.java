package com.uter.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name="rol")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rol {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name="Name",nullable = false, length = 20)
    private String name;
    
}