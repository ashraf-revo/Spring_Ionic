package org.revo.Service;

import org.revo.Domain.Post;
import org.revo.Domain.User;

import java.util.List;

/**
 * Created by revo on 7/29/16.
 */
public interface PostService {
    List<Post> posts(String id, int limit, User user);

    Post save(Post post);
}
