package org.revo.Service;

import org.revo.Domain.User;

import java.util.List;

/**
 * Created by revo on 7/28/16.
 */

public interface UserService {

    User find(User user);

    User save(User user);

    List<User> users(String id, int limit);

    void follow(User user, boolean state);


    List<User> followers(User user);

    List<User> following(User user);
}
