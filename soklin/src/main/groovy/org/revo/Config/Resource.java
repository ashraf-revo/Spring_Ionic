package org.revo.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Created by revo on 5/7/16.
 */
@EnableResourceServer
@Configuration
public class Resource extends ResourceServerConfigurerAdapter {
    @Autowired
    private LogoutHandler logoutHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api/register").permitAll()
                .antMatchers("/hello/info").permitAll()
                .antMatchers("/api/user/**").hasRole("USER")

                .and().logout().logoutSuccessHandler(logoutHandler)
                .and().csrf().disable();
    }
}
