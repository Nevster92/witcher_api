package com.witcher.witcher_api.service;

import com.witcher.witcher_api.model.pojo.Character;
import com.witcher.witcher_api.repository.CharacterRepo;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor
public class PermissionService {

    @Autowired
    CharacterRepo characterRepo;

    private String getUserId() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Jwt token = (Jwt) authentication.getPrincipal();
            return  token.getClaims().get("sub").toString();
    }

    public void characterPermission(Long characterId) throws Exception {
        Optional<Character> character = characterRepo.findById(characterId);

        if(!character.get().getUser().getId().equals(getUserId())){
            throw new Exception("No Permission!");
        }
    }


    public void weaponPermission( int weaponId) throws Exception {
//        try {
//            String weaponOwner = itemRepositoryJdbcImpl.getUserIdByWeaponId(weaponId);
//            if(!weaponOwner.equals(getUserId())){
//                throw new Exception("No Permission!");
//            }
//        }catch (Exception e){
//            throw new Exception("No Permission!");
//        }

    }

    public void armorPermission(int armorId) throws Exception {
//        try {
//            String armorOwner = itemRepositoryJdbcImpl.getUserIdByArmorId(armorId);
//            if(!armorOwner.equals(getUserId())){
//                throw new Exception("No Permission!");
//            }
//        }catch (Exception e){
//            throw new Exception("No Permission!");
//        }

    }
}
