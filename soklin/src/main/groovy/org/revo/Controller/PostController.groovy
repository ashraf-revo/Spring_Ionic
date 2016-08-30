package org.revo.Controller

import com.fasterxml.jackson.annotation.JsonView
import org.revo.Domain.Post
import org.revo.Domain.User
import org.revo.Service.PostService
import org.revo.Util.SoklinView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

/**
 * Created by revo on 7/29/16.
 */
@Controller
@RequestMapping("/api/post")
class PostController {
    @Autowired
    PostService postService

    @RequestMapping
    @JsonView(SoklinView.PPU)
    ResponseEntity posts(@RequestParam(value = "id", required = false) String id) {
        ResponseEntity.ok(postService.posts(id, 10,null))
    }
    @RequestMapping("/{uid}")
    @JsonView(SoklinView.PPU)
    ResponseEntity postsMe(@PathVariable String uid,@RequestParam(value = "id", required = false) String id) {

        ResponseEntity.ok(postService.posts(id, 10,new User(id:uid)))
    }

    @RequestMapping(method = RequestMethod.POST)
    @JsonView(SoklinView.PPU)
    ResponseEntity save(@RequestBody Post post) {
        ResponseEntity.ok(postService.save(post))
    }
}
