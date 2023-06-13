package com.electronicstore.serviceI.Impl;

import com.electronicstore.Config.PageableResponse;
import com.electronicstore.dtos.UserDto;
import com.electronicstore.entity.User;

import com.electronicstore.exception.ResourceNotFoundException;
import com.electronicstore.helper.Helper;
import com.electronicstore.repository.UserRepo;
import com.electronicstore.serviceI.UserServiceI;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserServiceI {

    private static Logger log = LoggerFactory.getLogger(UserServiceImp.class);

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * @author Akshay
     * @apiNote this method is implementation of create user
     * @param userDto
     * @return UserDto
     */


    @Override
    public UserDto createUser(UserDto userDto) {

        log.info("Start the creating User in UserServiceImpl: {}", userDto);
        //generate unique id
        String userId = UUID.randomUUID().toString();

        userDto.setUserId(userId);
        log.info("Convert Dto to User  in UserServiceImpl: {}", userDto);
        User user = this.dtoToUser(userDto);

        User saveUser = this.userRepo.save(user);
        log.info("Convert User to Dto  in UserServiceImpl: {}", userDto);
        UserDto userDto1 = this.userToDto(saveUser);
        log.info("Complated the creating User in UserServiceImpl: {}", userDto);
        return userDto1;
    }


    /**
     * @author Akshay
     * @apiNote this method is implementation of Update user
     * @param userDto
     * @param userId
     * @return UserDto
     */

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {

        log.info("Start the update User in UserServiceImpl: {}", userDto, userId);
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException());
        log.info(" set updated user  in UserServiceImpl: {}", userDto, userId);
        user.setName(userDto.getName());
        user.setGender(userDto.getGender());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        user.setImageName(userDto.getImageName());
        user.setEmail(userDto.getEmail());

        log.info("Save updated user  in UserServiceImpl: {}", userDto, userId);
        User updatedUser = this.userRepo.save(user);
        log.info("Convert User to Dto  in UserServiceImpl: {}", userDto, userId);
        UserDto userDto1 = this.userToDto(updatedUser);
        log.info("Complated the update User in UserServiceImpl: {}", userDto, userId);
        return userDto1;
    }

    /**
     * @author Akshay
     * @apiNote this method is implementation of get Single  user
     * @param userId
     * @return UserDto
     */

    @Override
    public UserDto getUserById(String userId) {
        log.info("Start the get Single User in UserServiceImpl: {}", userId);
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException());
        log.info("Convert User to Dto  in UserServiceImpl: {}", userId);
        UserDto userDto = this.userToDto(user);
        log.info("Complated the get single User in UserServiceImpl: {}", userId);
        return userDto;
    }

    /**
     * @author Akshay
     * @apiNote this method is implementation of get All  users
     * @param pageNumber
     * @param pageSize
     * @param sortBy
     * @param sortDir
     * @return PageableResponse<UserDto>
     */

    @Override
    public PageableResponse<UserDto> getAllUser(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        log.info("Start the get All Users in UserServiceImpl: {}",pageNumber,pageSize,sortBy,sortDir);

        Sort sort =(sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

       log.info("we are create pagegination...UserServiceImpl :{}",pageNumber,pageSize,sortBy,sortDir);
       //we are using -1 then start page is 0 and we are not using -1 then start the page no 1
        Pageable p = PageRequest.of(pageNumber, pageSize,sort);

        Page<User> page = this.userRepo.findAll(p);

        PageableResponse<UserDto> response = Helper.getpagableResponse(page, UserDto.class);


        log.info("Complated the get single User in UserServiceImpl: {}",pageNumber,pageSize,sortBy,sortDir);

        return response;
    }

    /**
     * @author Akshay
     * @apiNote this method is implementation of delete user
     * @param userId
     */


    @Override
    public void deleteUser(String userId) {
        log.info("Start the delete User in UserServiceImpl: {}", userId);
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException());
        log.info("Complated the delete User in UserServiceImpl: {}", userId);
        this.userRepo.delete(user);

    }


    /**
     * @author Akshay
     * @apiNote this method is implementation of get user By Email
     * @param email
     * @return UserDto
     */

    @Override
    public UserDto getUserByEmail(String email) {

        log.info("Start the  getUserByEmail in UserServiceImpl: {}", email);
        User user = userRepo.findByEmail(email).orElseThrow(() ->new ResourceNotFoundException());
        log.info("Convert User to Dtoin UserServiceImpl: {}", email);
        UserDto userDto = this.userToDto(user);
        log.info("Complate the  getUserByEmail in UserServiceImpl: {}", email);
        return userDto;
    }



    /**
     * @author Akshay
     * @apiNote this method is implementation of search User
     * @param keyword
     * @return List<UserDto>
     */

    @Override
    public List<UserDto> searchUser(String keyword) {

        log.info("Start the searchUser  in UserServiceImpl: {}", keyword);
        List<User> users = this.userRepo.findByNameContaining(keyword);

        log.info("Convert User to Dto by using stream in UserServiceImpl: {}", keyword);

        List<UserDto> userDto = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());

        log.info("Complated the searchUser  in UserServiceImpl: {}", keyword);
        return userDto;
    }

    // Convert Dto to User
    public User dtoToUser(UserDto userDto) {

        User user = this.modelMapper.map(userDto, User.class);

        return user;
    }

    // Convert User To Dto
    public UserDto userToDto(User user) {

        UserDto userDto = this.modelMapper.map(user, UserDto.class);

        return userDto;

    }


}
