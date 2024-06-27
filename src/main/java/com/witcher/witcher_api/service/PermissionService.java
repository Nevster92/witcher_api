package com.witcher.witcher_api.service;

import com.witcher.witcher_api.repository.CharacterRepository;
import com.witcher.witcher_api.repository.ItemRepository;
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
    ItemRepository itemRepositoryJdbcImpl;

    @Autowired
    UserRepository userRepositoryHibernateImpl;

    private String getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt token = (Jwt) authentication.getPrincipal();
        return  token.getClaims().get("sub").toString();
    }

    public void characterPermission(int characterId) throws Exception {
        if(!characterRepositoryJdbcImpl.hasAccesToCharacter(getUserId(), characterId)){
            throw new Exception("No Permission!");
        }
    }

    public void weaponPermission( int weaponId) throws Exception {
        try {
            String weaponOwner = itemRepositoryJdbcImpl.getUserIdByWeaponId(weaponId);
            if(!weaponOwner.equals(getUserId())){
                throw new Exception("No Permission!");
            }
        }catch (Exception e){
            throw new Exception("No Permission!");
        }

    }

}
