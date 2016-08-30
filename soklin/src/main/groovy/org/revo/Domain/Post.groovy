package org.revo.Domain

import com.fasterxml.jackson.annotation.JsonView
import org.revo.Util.SoklinView
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

import javax.persistence.Entity

/**
 * Created by revo on 7/29/16.
 */
@Document
@Entity
class Post {
    @Id
    @JsonView(SoklinView.Post)
    String id
    @JsonView(SoklinView.Post)
    String content
    @JsonView(SoklinView.Post)
    String image
    @CreatedDate
    @JsonView(SoklinView.Post)
    Date created
    @CreatedBy
    @DBRef
    @JsonView(SoklinView.PostUser)
    User user
    @Transient
    String file
}
