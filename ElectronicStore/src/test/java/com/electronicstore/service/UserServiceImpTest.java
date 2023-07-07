package com.electronicstore.service;

import com.electronicstore.dtos.UserDto;
import com.electronicstore.entity.User;
import com.electronicstore.model.PageableResponse;
import com.electronicstore.repository.UserRepo;
import com.electronicstore.serviceI.Impl.UserServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UserServiceImpTest {

    @MockBean
    private UserRepo userRepo;

    @Autowired
    private UserServiceImp userServiceImp;

    @Autowired
    private ModelMapper modelMapper;

    User user;

    User user1;

    User user2;

    List<User> userList;

    UserDto userDto;



    @BeforeEach
    public void init() {

       user = User.builder()
                .name("Akshay")
                .about("This is test create method ")
                .email("akshayghuge8080@gmail.com")
                .gender("male")
                .password("Akshay@123")
                .imageName("Akshay.png")
                .build();

          user1 = User.builder()
                .name("Suraj")
                .about("This is test create method ")
                .email("Surajghuge8080@gmail.com")
                .gender("male")
                .password("Suraj@123")
                .imageName("Suraj.png")
                .build();

          user2 = User.builder()
                .name("Ganesh")
                .about("This is test create method ")
                .email("ganeshnagare8080@gmail.com")
                .gender("male")
                .password("Ganesh@123")
                .imageName("Ganesh.png")
                .build();

         userList = Arrays.asList(user, user1, user2);

        userDto = UserDto.builder()
                .name("Akshay Ghuge")
                .about("This is update user about details")
                .gender("Male")
                .imageName("ABC.png")
                .email("akshayghuge8080@gmail.com")
                .build();

    }

    //create User

    @Test
    public void createUserTest() {

        //Arrange
        Mockito.when(userRepo.save(Mockito.any())).thenReturn(user);
        //Actual call
        UserDto user1 = userServiceImp.createUser(modelMapper.map(user, UserDto.class));

        System.out.println(user1.getName());
        //Assert
        Assertions.assertNotNull(user1);
        Assertions.assertEquals("Akshay", user1.getName());

    }

    //update

    @Test
    public void updateUserTest() {

        String userId = "ABCDEF";


        //Arrange
        Mockito.when(userRepo.findById(Mockito.anyString())).thenReturn(Optional.of(user));
        Mockito.when(userRepo.save(Mockito.any())).thenReturn(user);
        //Actual call
        UserDto updateUser1 = userServiceImp.updateUser(userDto, userId);

        System.out.println(updateUser1.getName());
        System.out.println(updateUser1.getImageName());
        //Assert
        Assertions.assertNotNull(userDto);
        Assertions.assertEquals(userDto.getName(),updateUser1.getName());



    }

    //get User By Id

    @Test
    void getUserByIdTest(){

        String userId="ABCD";

        //Arrange
        Mockito.when(userRepo.findById(Mockito.any())).thenReturn(Optional.of(user));
        //Actual Call
        UserDto userById = userServiceImp.getUserById(userId);
        System.out.println(userById.getName());
        //Assert
       // Assertions.assertNotNull(userDto);

        Assertions.assertEquals(user.getName(),userById.getName(),"Name not match");

    }

    // get All User
    @Test
    void getAllUserTest(){



        List<User> userList = Arrays.asList(user, user1, user2);

        PageImpl<User> page = new PageImpl<>(userList);
        //Arrange
        Mockito.when(userRepo.findAll((Pageable) Mockito.any())).thenReturn(page);
        //Actual call
        PageableResponse<UserDto> allUser = userServiceImp.getAllUser(1, 2, "name", "ascending");
        //Assert
        Assertions.assertEquals(3,allUser.getContent().size());

    }

    //Delete
    @Test
    void deleteUserTest(){

        String userId="ASDFG";
        //Arrange
        Mockito.when(userRepo.findById("ASDFG")).thenReturn(Optional.of(user));
        //Actual call
        userServiceImp.deleteUser(userId);

        Mockito.verify(userRepo,Mockito.times(1)).delete(user);



    }

    //get User By Email

    @Test
    void getUserByEmailTest(){

        String emailId="akshayghuge8080@gmail.com";
        //Arrange
        Mockito.when(userRepo.findByEmail(emailId)).thenReturn(Optional.of(user));
        //Actual call
        UserDto userDto1 = userServiceImp.getUserByEmail(emailId);
        //Assert
        //Assertions.assertNotNull(userDto1);
        Assertions.assertEquals(user.getEmail(),userDto.getEmail(),"Email Not Matched");


    }

    //search User
    @Test
    void searchUserTest(){

        String keywords="Akshay";
        //Arrange
        Mockito.when(userRepo.findByNameContaining(keywords)).thenReturn(Arrays.asList(user,user1,user2));
        //Actual Cal
        List<UserDto> userDtos = userServiceImp.searchUser(keywords);

        Assertions.assertEquals(3,userDtos.size(),"Size not match");

    }



}
