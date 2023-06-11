package com.electronicstore.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "CATEGORIES")
public class Category {
    @Id
    @Column(name = "Id")
    private String categoryId;
    @Column(name = "CATEGORY_TITLE",length = 40)
    private String title;
    @Column(name = "CATEGORY_DESCRIPTION",length = 65)
    private String Description;
    @Column(name = "CATEGORY_COVERIMAGE")
    private String coverImage;


}
