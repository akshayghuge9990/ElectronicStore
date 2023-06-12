package com.electronicstore.controller;

import com.electronicstore.Config.ApiResponse;
import com.electronicstore.Config.AppConstatnt;
import com.electronicstore.Config.ImageResponse;
import com.electronicstore.Config.PageableResponse;
import com.electronicstore.dtos.UserDto;
import com.electronicstore.serviceI.FileServiceI;
import com.electronicstore.serviceI.UserServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServiceI userServiceI;

    @Autowired
    private FileServiceI fileServiceI;

    @Value("${user.profile.image.path}")
    private String imageUploadPath;


//create User


    /**
     * @author Akshay
     * @apiNote this api is  used to  create User
     * @param userDto
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

    /**
     * @author Akshay
     * @apiNote this api is  used to  update User
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

    /**
     * @author Akshay
     * @apiNote this api is  used to  getSingleUser
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

    /**
     * @author Akshay
     * @apiNote this api is  used to  getAllUser
     * @param pageNumber
     * @param pageSize
     * @param sortBy
     * @param sortDir
     * @return UserResponse
     */


    @GetMapping("/users")
    public ResponseEntity<PageableResponse<UserDto>> getAllUser(
            @RequestParam(value = "pageNumber", defaultValue = AppConstatnt.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstatnt.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstatnt.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstatnt.SORT_DIR, required = false) String sortDir
    ) {
        log.info("Start the api method getAllUser in UserController : {}", pageNumber, pageSize, sortBy, sortDir);
        PageableResponse userResponse = this.userServiceI.getAllUser(pageNumber, pageSize, sortBy, sortDir);
        log.info("Complated the api method getAllUser in UserController : {}", pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<PageableResponse<UserDto>>(userResponse, HttpStatus.OK);

    }

    //Delete

    /**
     * @author Akshay
     * @apiNote this api is  used to  delete User
     * @param userId
     * @return ApiResponse
     */

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String userId) {
        log.info("Start the api method deleteUser in UserController : {}", userId);
        this.userServiceI.deleteUser(userId);
        log.info("Complated the api method deleteUser in UserController : {}", userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(AppConstatnt.USER_DELETED, true), HttpStatus.OK);


    }

    //find by email
    /**
     * @author Akshay
     * @apiNote this api is  used to  find By Email
     * @param email
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

    /**
     * @author Akshay
     * @apiNote this api is  used to  search Name
     * @param keyword
     * @return List<UserDto>
     */

    @GetMapping("/users/search/{keyword}")
    public ResponseEntity<List<UserDto>> searchName(@PathVariable String keyword) {
        log.info("Start the api method searchName in UserController : {}", keyword);
        List<UserDto> result = this.userServiceI.searchUser(keyword);

        log.info("Complated the api method searchName in UserController : {}", keyword);
        return new ResponseEntity<List<UserDto>>(result, HttpStatus.OK);

    }


    //upload user image

    /**
     * @author Akshay
     * @apiNote this api is  used to upload user image
     * @param image
     * @param userId
     * @return ImageResponse
     * @throws IOException
     */

    @PostMapping("/users/image/{userId}")
    public ResponseEntity<ImageResponse> uploadUserImage(@RequestParam("userImage") MultipartFile image, @PathVariable String userId) throws IOException {

        log.info("Start the api method upload image in UserController : {}",image,userId);
        String imageName = fileServiceI.uploadFile(image, imageUploadPath);

        UserDto user = userServiceI.getUserById(userId);

        user.setImageName(imageName);

        UserDto userDto = userServiceI.updateUser(user, userId);

        ImageResponse imageResponse = ImageResponse.builder().imageName(imageName).success(true).status(HttpStatus.CREATED).build();

        log.info("Completed the api method upload image in UserController : {}",image,userId);
        return new ResponseEntity<>(imageResponse, HttpStatus.CREATED);

    }





}
