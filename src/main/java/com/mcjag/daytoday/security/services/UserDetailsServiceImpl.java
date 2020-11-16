package com.mcjag.daytoday.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcjag.daytoday.tables.User;
import com.mcjag.daytoday.providers.UserProvider;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    UserProvider userProvider;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        User user = userProvider.findUserByEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return UserDetailsImpl.build(user);
    }
}