package com.opthema.twitter.service;

import com.opthema.twitter.entity.Following;
import com.opthema.twitter.entity.Users;
import com.opthema.twitter.repository.FollowingRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FollowingService implements IFollowingService {
    private FollowingRepository followingRepository;
    public FollowingService(FollowingRepository followingRepository) {
        this.followingRepository = followingRepository;
    }
    @Override
    public void requestForFollowUser(Users follower, Users followed) {
       Following following = new Following();
       following.setFollower(follower);
       following.setFollowed(followed);

       followingRepository.save(following);
    }
    @Override
    public void unFollowUser(Users follower, Users followed) {
        Following following = followingRepository.findByFollower_IdAndFollowed_Id(follower.getId(),followed.getId());
        followingRepository.delete(following);
    }

    @Override
    public Following getFollowing(Users follower, Users followed) {
        return followingRepository.findByFollower_IdAndFollowed_Id(follower.getId(),followed.getId());
    }

    @Override
    public void acceptFollowRequest(Following following) {
        followingRepository.save(following);
    }

    @Override
    public List<Following> getAllFollowingByFollowerId(Long id) {
        List <Following> listForReturn = followingRepository.findAllByFollower_Id(id);
        for (int i=0; i<listForReturn.size(); i++){
            if(listForReturn.get(i).isAccepted() == false){
                listForReturn.remove(i);
            }

        }
        return listForReturn;
    }

    @Override
    public List<Following> getAllFollowingByFollowedId(Long id) {
        List <Following> listForReturn = followingRepository.getAllByFollowed_Id(id);
        for (int i=0; i<listForReturn.size(); i++){
            if(listForReturn.get(i).isAccepted() == false){
               continue;
            }
            else{
                listForReturn.remove(i);
            }
        }
        return listForReturn;
    }

    @Override
    public List<Following> getAllFollowingByFollowedId2(Long id) {
        List <Following> listForReturn = followingRepository.findAllByFollowed_Id(id);
        System.out.println("*******"+listForReturn.size());
        for (int i=0; i<listForReturn.size(); i++){
            if(listForReturn.get(i).isAccepted() == false){
                listForReturn.remove(i);
                i--;
            }
            else{
               continue;
            }
        }
        return listForReturn;
    }

    @Transactional
    @Override
    public void deleteByFollowerOrFollowed(Long followerId, Long followedId) {
        followingRepository.deleteAllByFollowed_IdOrFollower_Id(followerId,followedId);
    }

    @Override
    public int countOfFollower(Long id) {
        List <Following> followings= followingRepository.findAllByFollower_Id(id);
        int counter = 0;
        for(int i=0; i<followings.size(); i++){
            if(followings.get(i).isAccepted() == false){
                continue;
            }
            else counter++;
        }
        return counter;
    }
    @Override
    public int countOfFollowed(Long id) {
        List <Following> followings= followingRepository.getAllByFollowed_Id(id);
        int counter = 0;
        for(int i=0; i<followings.size(); i++){
            if(followings.get(i).isAccepted() == false){
                continue;
            }
            else counter++;
        }
        return counter;

    }
}
