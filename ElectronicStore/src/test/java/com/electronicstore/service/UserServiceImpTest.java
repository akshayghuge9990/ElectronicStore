package com.electronicstore.service;

import com.electronicstore.dtos.UserDto;
import com.electronicstore.entity.User;
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

@SpringBootTest
public class UserServiceImpTest {

    @MockBean
    private UserRepo userRepo;

    @Autowired
    private UserServiceImp userServiceImp;

    @Autowired
    private ModelMapper modelMapper;

    User user;

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


    }

    //create User

    @Test
    public void createUserTest() {

        //Arrange
        Mockito.when(userRepo.save(Mockito.any())).thenReturn(user);
        //Act
        UserDto user1 = userServiceImp.createUser(modelMapper.map(user, UserDto.class));

        System.out.println(user1.getName());
        //Assert
        Assertions.assertNotNull(user1);
        Assertions.assertEquals("Akshay", user1.getName());

    }


}
