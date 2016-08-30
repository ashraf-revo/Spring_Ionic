package org.revo.Config

import com.cloudinary.Cloudinary
import com.cloudinary.utils.ObjectUtils
import org.revo.Domain.User
import org.revo.Repository.UserRepository
import org.revo.Service.SecurityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.mongodb.core.mapping.event.LoggingEventListener
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

/**
 * Created by revo on 7/28/16.
 */
@Configuration
@EnableSwagger2
class Util {
    @Autowired
    SecurityService securityService;


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    AuditorAware<User> aware() {
        { ->
            securityService.GetCurrentUser();
        }
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository, DefaultUser defaultUser, PasswordEncoder encoder) {
        { args ->
            if (userRepository.count() == 0) userRepository.save(getUser(defaultUser, encoder))
        }
    }

    @Bean
    CorsConfiguration configuration() {
        return new CorsConfiguration();
    }

    @Bean
    LoggingEventListener eventListener() {
        return new LoggingEventListener();
    }

    static User getUser(DefaultUser defaultUser, PasswordEncoder encoder) {
        return new User(username: defaultUser.username, password: encoder.encode(defaultUser.password))
    }

    @Bean
    public Cloudinary cloudinary(CloudinaryProperties cloudinaryProperties) {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudinaryProperties.getCloud_name(),
                "api_key", cloudinaryProperties.getApi_key(),
                "api_secret", cloudinaryProperties.getApi_secret()));
    }
}
