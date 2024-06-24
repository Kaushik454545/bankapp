package com.banking.banking.SecurityConfig;

import com.banking.banking.Repository.UserRepo;
import com.banking.banking.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
@Component

public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private HttpServletRequest httpServletRequest;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = this.userRepo.findByEmail(username);
        String url = this.httpServletRequest.getRequestURL().toString();


        if (url.contains("@gmail.com")||url.contains("@")) {
            if(url.contains(username))
            {

                if (user.isEmpty()) {
                    throw new UsernameNotFoundException(username);
                }
                CustomUserDetails customUserDetails = new CustomUserDetails(user.get());
                return customUserDetails;
            }
            else
            {
                throw new UsernameNotFoundException(username);
            }


        }
        else
        {

            if (user.isEmpty()) {
                throw new UsernameNotFoundException(username);
            }
            CustomUserDetails customUserDetails = new CustomUserDetails(user.get());
            return customUserDetails;
        }
    }

}
