package org.revo.Repository;

import org.revo.Domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by revo on 7/28/16.
 */
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);

    List<User> findAllByIdIn(List<String> ids);

    List<User> findByFollowersContaining(User user);
}
