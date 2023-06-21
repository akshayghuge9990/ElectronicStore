package com.electronicstore.dtos;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    private String productId;
    @NotEmpty
    @Column(name = "PRODUCT_TITLE")
    @Size(min = 3,max = 15,message = "Product Title must be min 3 char and max 15 char allwoed")
    private String title;
    @NotEmpty
    @Size(min = 4,max = 80,message = "Product Description must be min 4 char and max 80 char allwoed")
    @Column(name = "PRODUCT_DESCRIPTION")
    private String description;
    @Column(name = "PRODUCT_PRICE")
    private int price;
    @Column(name = "PRODUCT_QUANTITY")
    private int quantity;
    @Column(name = "PRODUCT_ADDEDDATE")
    private Date addedDate;
    @Column(name = "PRODUCT_LIVE")
    private boolean live;
    @Column(name = "PRODUCT_STOCK")
    private boolean stock;


}
