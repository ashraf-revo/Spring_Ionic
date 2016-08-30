package org.revo.Service.Impl;

import org.revo.Domain.User;
import org.revo.Service.SecurityService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by revo on 7/28/16.
 */
@Service
public class SecurityServiceImpl implements SecurityService {
    @Override
    public User GetCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((User) authentication.getPrincipal());
    }
}
