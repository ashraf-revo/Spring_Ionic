package org.revo.Repository;

import org.revo.Domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by revo on 7/29/16.
 */
public interface PostRepository extends MongoRepository<Post, String> {
}
