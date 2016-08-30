package org.revo.Service.Impl;

import org.revo.Domain.Post;
import org.revo.Domain.User;
import org.revo.Service.SimpMessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by revo on 7/29/16.
 */
@Service
public class SimpMessagingServiceImpl implements SimpMessagingService {
    @Autowired
    SimpMessagingTemplate template;
    private final String postPath = "/topic/post";

    @Override
    public void send(Post post) {
        Set<User> followers = post.getUser().getFollowers();
        post.getUser().setPosts(null);
        post.getUser().setFollowers(null);
        followers.forEach(user -> System.out.println(user.getUsername()));
        template.convertAndSend(postPath, post);

    }

    @Override
    public void send(User user, Post post) {
        post.getUser().setPosts(null);
        post.getUser().setFollowers(null);
        template.convertAndSendToUser(user.getUsername(), postPath, post);
    }

    @Override
    public void send(List<User> users, Post post) {
        users.forEach(user -> send(user, post));
    }
}
