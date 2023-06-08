package com.electronicstore.serviceI;

import com.electronicstore.Config.UserResponse;
import com.electronicstore.dtos.UserDto;

import java.util.List;

public interface UserServiceI {

    //create

    UserDto createUser(UserDto userDto);

    //update

    UserDto updateUser(UserDto userDto, String userId);

    //getSingle

    UserDto getUserById(String userId);

    //getAll

    UserResponse getAllUser(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);

    //Delete

    void deleteUser(String userId);

    //get Single User by email

    UserDto getUserByEmail(String email);


    //Search User

    List<UserDto> searchUser(String keyword);


}
