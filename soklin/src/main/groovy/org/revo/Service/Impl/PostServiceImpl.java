package org.revo.Service.Impl;

import org.bson.types.ObjectId;
import org.revo.Domain.Post;
import org.revo.Domain.User;
import org.revo.Repository.PostRepository;
import org.revo.Service.CloudinaryService;
import org.revo.Service.PostService;
import org.revo.Service.SecurityService;
import org.revo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;
import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * Created by revo on 7/29/16.
 */
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;
    @Autowired
    MongoOperations mp;
    @Autowired
    SecurityService securityService;
    @Autowired
    UserService userService;
    @Autowired
    CloudinaryService cloudinaryService;

    @Override
    public List<Post> posts(String id, int limit, User user) {
        ObjectId o;
        if (id != null && !id.trim().isEmpty()) o = new ObjectId(id);
        else o = new ObjectId();
        User currentUser = securityService.GetCurrentUser();
        List<User> f = new ArrayList<>();
        if (user == null) {
            f.addAll(userService.following(currentUser));
            f.add(currentUser);
        } else f.add(user);
        return mp.find(new Query(where("id").lt(o).and("user").in(f)).limit(limit).with(new Sort(DESC, "id")), Post.class);
    }

    @Override
    public Post save(Post post) {
        if (post.getFile() != null && !post.getFile().isEmpty()) {
            post.setImage(cloudinaryService.Upload(post.getFile()));
            post.setFile("");
        }
        Post saved = postRepository.save(post);
        mp.updateFirst(new Query(where("id").is(saved.getUser().getId())), new Update().addToSet("posts", saved), User.class);
        return saved;
    }
}
