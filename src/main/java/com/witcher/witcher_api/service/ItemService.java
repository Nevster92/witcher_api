package com.witcher.witcher_api.service;


import com.witcher.witcher_api.model.pojo.Weapon;
import com.witcher.witcher_api.repository.CharacterRepository;
import com.witcher.witcher_api.repository.CharacterRepositoryJdbcImpl;
import com.witcher.witcher_api.repository.ItemRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    PermissionService permissionService;

    @Autowired
    CharacterRepositoryJdbcImpl characterRepository;


    public Weapon getWeapon(int weaponId) throws Exception {
        permissionService.weaponPermission(weaponId);
        return itemRepository.selectWeapon(weaponId);
    }

    public List<Weapon> getAllWeaponsByCharacter(int characterId){
        return itemRepository.selectAllWeapons(characterId);
    }

    public int createNewWeapon(Weapon weapon, int characterId) {
        return itemRepository.createNewWeapon(weapon, characterId);
    }

    public void deleteWeapon(int weaponId) throws Exception {
        permissionService.weaponPermission(weaponId);
        int characterId = itemRepository.selectWeapon(weaponId).getCharacter_id();
        // delete all the Foregn Keys
            characterRepository.setWeaponToNull(weaponId,characterId);
        // delete weapon
            itemRepository.deleteWeapon(weaponId);
    }

    public Weapon setWeaponAttributes( Weapon weapon) throws Exception {
        permissionService.weaponPermission(weapon.getId());
        itemRepository.updateWeaponAttributes(weapon);
        return itemRepository.selectWeapon(weapon.getId());
    }
}
