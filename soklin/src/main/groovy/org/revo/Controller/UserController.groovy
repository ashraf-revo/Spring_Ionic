package org.revo.Controller

import com.fasterxml.jackson.annotation.JsonView
import org.revo.Domain.User
import org.revo.Repository.UserRepository
import org.revo.Service.SecurityService
import org.revo.Service.UserService
import org.revo.Util.SoklinView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

/**
 * Created by revo on 5/12/16.
 */
@Controller
@RequestMapping("api/user")
class UserController {
    @Autowired
    SecurityService securityService
    @Autowired
    UserService userService
    @Autowired
    UserRepository userRepository

    @RequestMapping
    @JsonView(SoklinView.User)
    public ResponseEntity Account() {
        ResponseEntity.ok(securityService.GetCurrentUser())
    }

    @RequestMapping("/{id}")
    @JsonView(SoklinView.User)
    public ResponseEntity AccountUser(@PathVariable("id") String id) {
        ResponseEntity.ok(userService.find(new User(id: id)))
    }

    @RequestMapping("all")
    @JsonView(SoklinView.User)
    public ResponseEntity all(@RequestParam(value = "id", required = false) String id) {
        ResponseEntity.ok(userService.users(id, 10))
    }

    @RequestMapping("{id}/followers")
    @JsonView(SoklinView.User)
    public ResponseEntity followers(@PathVariable("id") String id) {
        ResponseEntity.ok(userService.followers(new User(id: id)))
    }

    @RequestMapping("{id}/following")
    @JsonView(SoklinView.User)
    public ResponseEntity following(@PathVariable("id") String id) {
        ResponseEntity.ok(userService.following(new User(id: id)))
    }

    @RequestMapping("follow/{id}")
    public ResponseEntity follow(
            @PathVariable String id, @RequestParam(name = "state", defaultValue = "false") boolean state) {
        userService.follow(new User(id: id), state)
        ResponseEntity.noContent().build()
    }
}
