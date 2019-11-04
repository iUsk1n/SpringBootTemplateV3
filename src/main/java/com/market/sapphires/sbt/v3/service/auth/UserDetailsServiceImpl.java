package com.market.sapphires.sbt.v3.service.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.market.sapphires.sbt.v3.entity.LoginUser;
import com.market.sapphires.sbt.v3.mapper.LoginUserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final LoginUserMapper dao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginUser user = this.dao.selectByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }

        return user;
    }

}
