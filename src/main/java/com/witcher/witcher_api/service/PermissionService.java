package com.witcher.witcher_api.service;

import com.witcher.witcher_api.repository.CharacterRepository;
import com.witcher.witcher_api.repository.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class PermissionService {

    @Autowired
    CharacterRepository characterRepositoryJdbcImpl;

    @Autowired
    UserRepository userRepositoryHibernateImpl;

    private String getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt token = (Jwt) authentication.getPrincipal();
        return  token.getClaims().get("sub").toString();
    }

    public boolean hasPermissionToCharacter(int characterId) {
        return characterRepositoryJdbcImpl.hasAccesToCharacter(getUserId(), characterId);
    }

}
