package com.electronicstore.dtos;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {

    private String categoryId;
    @NotEmpty
    @Size(min = 3, max = 17, message = "Title must be min 3 char and max 17 char allowed")
    private String title;
    @NotEmpty
    @Size(min = 4, max = 25, message = "Descreption must be min 4 char and max 25 char allowed ")
    private String Description;
    @NotEmpty
    private String coverImage;

}
