package com.opthema.twitter.service;

import com.opthema.twitter.entity.User;
import com.opthema.twitter.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService implements IUserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(Long userId) {
        return userRepository.getUserById(userId);
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
