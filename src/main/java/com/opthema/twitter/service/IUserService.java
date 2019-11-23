package com.opthema.twitter.service;

import com.opthema.twitter.entity.User;

public interface IUserService {

    User getUser(Long userId);
    void saveUser(User user);
    void deleteUser(Long userId);
}
