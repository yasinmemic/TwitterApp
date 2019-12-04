package com.opthema.twitter.service;

import com.opthema.twitter.entity.Users;

public interface IUserService {

    Users getUser(Long userId);
    void saveUser(Users user);
    void deleteUser(Long userId);
    Users getUserByUserName(String userName);
}
