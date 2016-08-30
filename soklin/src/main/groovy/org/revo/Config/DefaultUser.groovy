package org.revo.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by revo on 7/28/16.
 */
@Component
@ConfigurationProperties("default-user")
public class DefaultUser {
    String username
    String password
}
