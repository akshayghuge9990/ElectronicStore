package com.electronicstore.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {
    @Id
    private String productId;

    private String title;
    @Column(length = 10000)
    private String description;

    private int price;

    private int quantity;

    private Date addedDate;

    private boolean live;

    private boolean stock;


}
