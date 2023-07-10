package com.electronicstore.controller;

import com.electronicstore.dtos.UserDto;
import com.electronicstore.entity.User;
import com.electronicstore.model.ImageResponse;
import com.electronicstore.model.PageableResponse;
import com.electronicstore.serviceI.UserServiceI;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @MockBean
    private UserServiceI userServiceI;

    @Autowired
    private UserController userController;


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MockMvc mockMvc;

    User user;

    User user1;

    User user2;

    List<User> userList;

    UserDto userDto;

    UserDto userDto1;

    UserDto userDto2;

    UserDto userDto3;


    List<UserDto> UserDtoList;

    @BeforeEach
    void init() {

        user = User.builder()
                .name("Akshay")
                .about("This is test create method ")
                .email("akshayghuge8080@gmail.com")
                .gender("male")
                .password("Akshay@123")
                .imageName("687aee31-4e2b-42e4-8b76-e92ab9188980.png")
                .build();

        user1 = User.builder()
                .name("Akshay Ghuge")
                .about("This is update user about details")
                .gender("Male")
                .imageName("ABC.png")
                .email("akshayghuge8080@gmail.com")
                .build();


        user2 = User.builder()
                .name("Ganesh Nagare")
                .about("This is update user about details")
                .gender("Male")
                .imageName("GDH.png")
                .email("ganeshnagare8080@gmail.com")
                .build();

        userList= Arrays.asList(user,user1,user2);


        userDto = UserDto.builder()
                .name("Akshay Ghuge")
                .about("This is update user about details")
                .gender("Male")
                .imageName("ABC.png")
                .email("akshayghuge8080@gmail.com")
                .build();


        userDto1 = UserDto.builder()
                .name("Ganesh Nagare")
                .about("This is update user about details")
                .gender("Male")
                .imageName("GDH.png")
                .email("ganeshnagare8080@gmail.com")
                .build();

        userDto2 = UserDto.builder()
                .name("Aniket Nagare")
                .about("This is update user about details")
                .gender("Male")
                .imageName("YEG.png")
                .email("aniketnagare8080@gmail.com")
                .build();

        userDto3 = UserDto.builder()
                .name("Vaishnavi Nagare")
                .about("This is update user about details")
                .gender("Male")
                .imageName("ACD.png")
                .email("vaishnavinagare8080@gmail.com")
                .build();

        UserDtoList = Arrays.asList(userDto, userDto1, userDto2, userDto3);
    }

    //create user

    @Test
    void createUserTest() throws Exception {

        //follow
        // /api/users+POST+user data as json
        // data as json+status created

        UserDto dto = modelMapper.map(user, UserDto.class);

        Mockito.when(userServiceI.createUser(Mockito.any())).thenReturn(dto);

        //Actual request for url
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectTOJsonString(user))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").exists());

    }

    // when the request send to server then object will be converted into json form
    // this method is used ony for the  convertting  one object to json format

    private String convertObjectTOJsonString(Object user) {

        try {

            return new ObjectMapper().writeValueAsString(user);

        } catch (Exception ex) {

            ex.printStackTrace();
            return null;

        }
    }
    //update

    @Test
    void updateUserTest() throws Exception {

        // /api/users/{userId}+PUT request +json

        String userId = "1234";

        UserDto dto = this.modelMapper.map(user, UserDto.class);

        Mockito.when(userServiceI.updateUser(Mockito.any(), Mockito.anyString())).thenReturn(dto);

        this.mockMvc.perform(MockMvcRequestBuilders.put("/api/users/" + userId)

                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectTOJsonString(user))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").exists());

    }

    //get Single

    @Test
    void getSingleUserTest() throws Exception {

        //  /api//user/{userId}+Get request +json

        String userId="1234";

        UserDto dto = this.modelMapper.map(user, UserDto.class);

        Mockito.when(userServiceI.getUserById(Mockito.any())).thenReturn(dto);

        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/api/users/"+userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                .andExpect(status().isOk());



    }

    //get All

    @Test
    void getAllUserTest() throws Exception {

        PageableResponse<UserDto> pagableResponse = new PageableResponse<>();

        pagableResponse.setContent(Arrays.asList(userDto, userDto1, userDto2, userDto3));
        pagableResponse.setLastPage(false);
        pagableResponse.setPageSize(10);
        pagableResponse.setPageNumber(100);
        pagableResponse.setTotalPages(1000);

        Mockito.when(userServiceI.getAllUser(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString(), Mockito.anyString())).thenReturn(pagableResponse);
        // Actual call
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/users/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    //delete

    @Test
    void deleteUserTest() throws Exception {

       //   /api/users/{userId}+DELETE request+json

        String userId="ASDFX";

        userServiceI.deleteUser(userId);

        this.mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/users/"+userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                .andExpect(status().isOk());






    }

   // find by Email Id

    @Test
    void findByEmailTest() throws Exception {

        //  /api//users/{email}+Get request +json

        String email="akshayghuge8080@gmail.com";

        UserDto dto = this.modelMapper.map(user, UserDto.class);

        Mockito.when(userServiceI.getUserByEmail(Mockito.any())).thenReturn(dto);

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/users/"+email)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());




    }

    //search Name
    @Test
    void searchNameTest() throws Exception {

        //  /api/users/search/{keyword}+get request+json

        String name="Akshay";


        List<UserDto> dtoList = userList.stream().map((u) -> modelMapper.map(u, UserDto.class)).collect(Collectors.toList());

        Mockito.when(userServiceI.searchUser(Mockito.any())).thenReturn(dtoList);

        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/api/users/search/"+name)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                .andExpect(status().isOk());

    }

    //upload Image(Problem!!!!!!!!!!!!)

    @Test
    void uploadUserImageTest() throws Exception {

        //   /api/users/image/{userId}+POST request+json

        String userId="1234";


        UserDto dto = modelMapper.map(user, UserDto.class);

        Mockito.when(userServiceI.updateUser(Mockito.any(),Mockito.anyString())).thenReturn(dto);

        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/api/users/image/"+userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectTOJsonString(user))
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.imageName").exists());





    }
}