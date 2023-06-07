package com.electronicstore.dtos;

import com.electronicstore.validate.ImageNameValid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String userId;

    @NotEmpty
    @Size(min = 4, max = 20, message = "UserDto name must be minimum char are 4 and maximum character are  20 allowed")
    private String name;
    @NotEmpty
    @Size(min = 3, max = 15, message = "UserDto password must be minimum 3 char and maximum char 15 allowed")
    private String password;
    @NotEmpty
    private String gender;
    @NotEmpty
    @Size(min = 4, max = 60, message = "UserDto about must be min 4 char and max 60 char allowed")
    private String about;
    @NotEmpty
    private String imageName;

    @ImageNameValid
    private String email;


}
