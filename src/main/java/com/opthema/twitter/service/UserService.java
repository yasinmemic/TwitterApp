package com.opthema.twitter.service;

import com.opthema.twitter.entity.Tweet;
import com.opthema.twitter.entity.Users;
import com.opthema.twitter.repository.TweetRepository;
import com.opthema.twitter.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService implements IUserService {

    private UserRepository userRepository;
    private TweetRepository tweetRepository;
    private ITweetService tweetService;
    public UserService(UserRepository userRepository,TweetRepository tweetRepository,ITweetService tweetService) {
        this.userRepository = userRepository;
        this.tweetRepository = tweetRepository;
        this.tweetService = tweetService;
    }

    @Override
    public Users getUser(Long userId) {
        return userRepository.getUserById(userId);
    }

    @Transactional
    @Override
    public void saveUser(Users user) {
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteUser(Long userId) {
        List<Tweet> tweets = tweetRepository.getTweetsByUserIdOrderByCreatedAtDesc(userId);

        for(int i=0; i<tweets.size(); i++){
            tweetService.deleteTweet(tweets.get(i).getId());
        }
        userRepository.deleteById(userId);
    }
    @Override
    public Users getUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
