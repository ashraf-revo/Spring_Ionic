package org.revo.Domain

import com.fasterxml.jackson.annotation.JsonView
import org.revo.Domain.base.AbstractUser
import org.revo.Util.SoklinView
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

import javax.persistence.Entity

/**
 * Created by revo on 7/28/16.
 */
@Document
@Entity
class User extends AbstractUser {
    @Id
    @JsonView(SoklinView.User)
    String id
    @Indexed(unique = true)
    @JsonView(SoklinView.User)
    String username
    String password
    @JsonView(SoklinView.User)
    String image = "http://www.entando.com/portal/resources/cms/images/feature-user-management_d8.png"
    @DBRef
    @JsonView(SoklinView.UserPost)
    List<Post> posts = []
    @DBRef(lazy = true)
    @JsonView(SoklinView.Followers)
    Set<User> followers = []
    @Transient
    String file
}