package com.witcher.witcher_api.service;


import com.witcher.witcher_api.model.pojo.User;
import com.witcher.witcher_api.repository.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;


@Service
@NoArgsConstructor
public class UserService {

    @Autowired
    UserRepository userRepositoryHibernateImpl;

    private String getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt token = (Jwt) authentication.getPrincipal();
        return  token.getClaims().get("sub").toString();
    }

    public User getCurrentUser()
    {
        return userRepositoryHibernateImpl.getUserById(getUserId());
    }


    public void updateUser(User user){
        userRepositoryHibernateImpl.updateUser(getUserId(), user);
    }


}
