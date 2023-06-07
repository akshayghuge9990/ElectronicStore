package com.electronicstore.controller;

import com.electronicstore.Config.ApiResponse;
import com.electronicstore.Config.AppConstatnt;
import com.electronicstore.dtos.UserDto;
import com.electronicstore.serviceI.UserServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServiceI userServiceI;

//create User

    /*
     * @author Akshay
     * @apiNote this api is  used to  createUser
     * @param userDto
     * @param
     * @return UserDto
     */

    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {

        log.info("start the api method createUser in UserController : {}", userDto);
        UserDto createUserDto = this.userServiceI.createUser(userDto);

        log.info("Complated the api method createUser in UserController : {}", userDto);
        return new ResponseEntity<UserDto>(createUserDto, HttpStatus.CREATED);


    }

    // update User

    /*
     * @author Akshay
     * @apiNote this api is  used to  updateUser
     * @param userDto
     * @param userId
     * @return UserDto
     */

    @PutMapping("users/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable String userId) {

        log.info("start the api method updateUser in UserController : {}", userDto, userId);
        UserDto updateUser = this.userServiceI.updateUser(userDto, userId);

        log.info("Complated the api method updateUser in UserController : {}", userDto, userId);
        return new ResponseEntity<UserDto>(updateUser, HttpStatus.OK);

    }

    //Get Single User
    /*
     * @author Akshay
     * @apiNote this api is  used to  getSingleUser
     * @param
     * @param userId
     * @return UserDto
     */


    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable String userId) {
        log.info("start the api method getSingleUser in UserController : {}", userId);
        UserDto user = this.userServiceI.getUserById(userId);
        log.info("Complated the api method getSingleUser in UserController : {}", userId);
        return new ResponseEntity<UserDto>(user, HttpStatus.OK);

    }

    // Get All User

    /*
     * @author Akshay
     * @apiNote this api is  used to  getAllUser
     * @param
     * @param
     * @return List<UserDto>
     */


    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUser() {
        log.info("Start the api method getAllUser in UserController : {}");
        List<UserDto> users = this.userServiceI.getAllUser();
        log.info("Complated the api method getAllUser in UserController : {}");
        return new ResponseEntity<List<UserDto>>(users, HttpStatus.OK);

    }

    //Delete

    /*
     * @author Akshay
     * @apiNote this api is  used to  deleteUser
     * @param userId
     * @param
     * @return ApiResponse
     */

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String userId) {
        log.info("Start the api method deleteUser in UserController : {}");
        this.userServiceI.deleteUser(userId);
        log.info("Complated the api method deleteUser in UserController : {}");
        return new ResponseEntity<ApiResponse>(new ApiResponse(AppConstatnt.USER_DELETED, true), HttpStatus.OK);


    }
     // get by email
    /*
     * @author Akshay
     * @apiNote this api is  used to  findByEmail
     * @param email
     * @param
     * @return UserDto
     */

    @GetMapping("/users/{email}")
    public ResponseEntity<UserDto> findByEmail(@PathVariable String email) {

        log.info("Start the api method findByEmail in UserController : {}", email);
        UserDto userDto = this.userServiceI.getUserByEmail(email);

        log.info("Complated the api method findByEmail in UserController : {}", email);
        return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);

    }
     //Search

    /*
     * @author Akshay
     * @apiNote this api is  used to  searchName
     * @param keyword
     * @param
     * @return List<UserDto>
     */

    @GetMapping("/users/search/{keyword}")
    public ResponseEntity<List<UserDto>> searchName(@PathVariable String keyword) {
        log.info("Start the api method searchName in UserController : {}", keyword);
        List<UserDto> result = this.userServiceI.searchUser(keyword);

        log.info("Complated the api method searchName in UserController : {}", keyword);
        return new ResponseEntity<List<UserDto>>(result, HttpStatus.OK);

    }


}
