package com.witcher.witcher_api.repository;

import com.witcher.witcher_api.model.pojo.Armor;
import com.witcher.witcher_api.model.pojo.Weapon;

import java.util.List;

public interface ItemRepository {

    public List<Weapon> selectAllWeapons(int characterId);
    public List<Armor> selectAllArmors(int characterId);

    public Weapon selectWeapon(int weaponId);
    public Armor selectArmor(int armorId);

    public String getUserIdByWeaponId(int weaponId);
    public String getUserIdByArmorId(int armorId);

    public int createNewWeapon(Weapon weapon, int characterId);
    public int createNewArmor(Armor armor, int characterId);

    public void deleteWeapon(int weaponId);
    public void deleteArmor(int armorId);

    public Weapon updateWeaponAttributes(Weapon weapon);
    public Armor updateArmorAttributes(Armor armor);



}
