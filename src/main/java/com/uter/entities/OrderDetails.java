package com.uter.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_details", uniqueConstraints = {@UniqueConstraint(columnNames = {"orders_id", "products_id"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails implements Serializable {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="orders_id",nullable = false)
    private Orders orders;

    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="products_id",nullable = false)
    private Products products;

    @Column(name = "unit_price", nullable = false)
    private float unitPrice;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "discount", nullable = true)
    private int discount;

}
