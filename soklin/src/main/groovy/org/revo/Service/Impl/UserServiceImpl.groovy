package org.revo.Service.Impl

import com.mongodb.BasicDBObject
import org.bson.types.ObjectId
import org.revo.Domain.User
import org.revo.Repository.UserRepository
import org.revo.Service.SecurityService
import org.revo.Service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Service

import static org.springframework.data.domain.Sort.Direction.DESC
import static org.springframework.data.mongodb.core.query.Criteria.where

/**
 * Created by revo on 7/28/16.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    MongoOperations mp;
    @Autowired
    SecurityService securityService;

    @Override
    User find(User user) {
        return userRepository.findOne(user.id)
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    List<User> users(String id, int limit) {
        ObjectId o;
        if (id != null && !id.trim().isEmpty()) o = new ObjectId(id);
        else o = new ObjectId();
        return mp.find(new Query(where("id").lt(o).ne(securityService.GetCurrentUser().id)).limit(limit).with(new Sort(DESC, "id")), User.class);
    }

    @Override
    public void follow(User user, boolean state) {
        Update Followers = new Update();
        if (state) {
            Followers.addToSet("followers", securityService.GetCurrentUser())
        } else {
            Followers.pull("followers", securityService.GetCurrentUser())
        }
        mp.updateFirst(new Query(where("id").is(user.getId())), Followers, User.class);
    }


    @Override
    public List<User> followers(User user) {
        List<String> ids = mp.getCollection("user").findOne(new BasicDBObject("_id", new ObjectId(user.id)))["followers"].iterator().collect {
            it["id"]
        } as List<String>
        userRepository.findAllByIdIn(ids)
    }

    @Override
    public List<User> following(User user) {
        userRepository.findByFollowersContaining(user)
    }
}
