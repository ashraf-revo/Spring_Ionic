package org.revo.Controller

import org.revo.Domain.User
import org.revo.Repository.UserRepository
import org.revo.Service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

/**
 * Created by ashraf on 8/17/2016.
 */
@Controller
@RequestMapping("api")
class MainController {
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder encoder
    @Autowired
    UserRepository userRepository

    @RequestMapping("register")
    public ResponseEntity Account(@RequestBody User user) {
        if (userRepository.findByUsername(user.username).present) return ResponseEntity.badRequest().build()
        user.password = encoder.encode(user.password)
        userService.save(user)
        return ResponseEntity.ok().build()
    }

}
