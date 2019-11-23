package com.opthema.twitter.controller;

import com.opthema.twitter.entity.User;
import com.opthema.twitter.model.UserRequest;
import com.opthema.twitter.model.UserResponse;
import com.opthema.twitter.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController extends BaseController {

    IUserService userService;

    public UserController(ModelMapper modelMapper, IUserService userService) {
        super(modelMapper);
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody UserRequest userRequest){
            User user = map(userRequest,User.class);
            userService.saveUser(user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value="/users/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        userService.deleteUser(userId);
    }
}
