package org.revo.Service;

import org.revo.Domain.Post;
import org.revo.Domain.User;

import java.util.List;

/**
 * Created by revo on 7/29/16.
 */
public interface SimpMessagingService {
    void send(Post post);

    void send(User user, Post post);

    void send(List<User> users, Post post);
}