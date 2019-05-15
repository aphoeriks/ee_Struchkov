package com.accenture.flowerShop.service;

import com.accenture.flowerShop.dao.AccountDAO;
import com.accenture.flowerShop.entity.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyDBAuthenticationService implements UserDetailsService {

    @Autowired
    private AccountDAO accountDAO;
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BCryptPasswordEncoder encoder = passwordEncoder();

        Account account = accountDAO.findAccount(username);
        System.out.println("Account= " + account);

        if (account == null) {
            throw new UsernameNotFoundException("User " //
                    + username + " was not found in the database");
        }
        String role = account.getRole();

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);

        grantList.add(authority);

        boolean enabled = account.isActive();
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        UserDetails userDetails = (UserDetails) new User(account.getLogin(), //
                encoder.encode(account.getPassword()), enabled, accountNonExpired, //
                credentialsNonExpired, accountNonLocked, grantList);

        return userDetails;
    }

}