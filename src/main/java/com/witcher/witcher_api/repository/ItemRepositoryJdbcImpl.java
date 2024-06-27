package com.witcher.witcher_api.repository;

import com.witcher.witcher_api.model.pojo.Armor;
import com.witcher.witcher_api.model.pojo.Weapon;
import jakarta.persistence.Persistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
@Repository
public class ItemRepositoryJdbcImpl implements ItemRepository{

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate jdbcNamed;

    @Autowired
    public ItemRepositoryJdbcImpl(JdbcTemplate jdbcTemplate ,NamedParameterJdbcTemplate jdbcNamed){
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcNamed = jdbcNamed;
    }

    @Override
    public List<Weapon> selectAllWeapons(int characterId) {
        String sql = "SELECT * FROM weapon WHERE character_id = ?";
        return jdbcTemplate.query(sql, new Object[]{characterId}, new BeanPropertyRowMapper<>(Weapon.class));
    }

    @Override
    public List<Armor> selectAllArmors(int characterId) {
        String sql = "SELECT * FROM armor WHERE character_id = ?";
        return jdbcTemplate.query(sql, new Object[]{characterId}, new BeanPropertyRowMapper<>(Armor.class));
    }

    @Override
    public Weapon selectWeapon(int weaponId) {
        String sql = "SELECT * FROM weapon WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{weaponId}, new BeanPropertyRowMapper<>(Weapon.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Armor selectArmor(int armorId) {
        String sql = "SELECT * FROM armor WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{armorId}, new BeanPropertyRowMapper<>(Armor.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }



    @Override
    public int createNewWeapon(Weapon weapon, int characterId) {
        String sql = "INSERT INTO weapon (name, type, weapon_accuracy, availability, damage, reliability, hands_required, range, concealment, enhancements, weight, cost, category, effect, character_id) " +
                "VALUES (:name, :type, :weaponAccuracy, :availability, :damage, :reliability, :handsRequired, :range, :concealment, :enhancements, :weight, :cost, :category, :effect, :characterId)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        Map<String, Object> params = new HashMap<>();
        params.put("name", weapon.getName());
        params.put("type", weapon.getType());
        params.put("weaponAccuracy", weapon.getWeapon_accuracy());
        params.put("availability", weapon.getAvailability());
        params.put("damage", weapon.getDamage());
        params.put("reliability", weapon.getReliability());
        params.put("handsRequired", weapon.getHands_required());
        params.put("range", weapon.getRange());
        params.put("concealment", weapon.getConcealment());
        params.put("enhancements", weapon.getEnhancements());
        params.put("weight", weapon.getWeight());
        params.put("cost", weapon.getCost());
        params.put("category", weapon.getCategory());
        params.put("effect", weapon.getEffect());
        params.put("characterId", characterId);

        jdbcNamed.update(sql, new MapSqlParameterSource(params), keyHolder, new String[]{"id"});

        return keyHolder.getKey().intValue();
    }

    @Override
    public int createNewArmor(Armor armor, int characterId) {
        String sql = "INSERT INTO armor (name, category, stopping_power, armor_enhancement, availability, effect, encumbrance_value, weight, price, character_id) " +
                "VALUES (:name, :category, :stoppingPower, :armorEnhancement, :availability, :effect, :encumbranceValue, :weight, :price, :characterId)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        Map<String, Object> params = new HashMap<>();
        params.put("name", armor.getName());
        params.put("category", armor.getCategory());
        params.put("stoppingPower", armor.getStopping_power());
        params.put("armorEnhancement", armor.getArmor_enhancement());
        params.put("availability", armor.getAvailability());
        params.put("effect", armor.getEffect());
        params.put("encumbranceValue", armor.getEncumbrance_value());
        params.put("weight", armor.getWeight());
        params.put("price", armor.getPrice());
        params.put("characterId", characterId);

        jdbcNamed.update(sql, new MapSqlParameterSource(params), keyHolder, new String[]{"id"});

        return keyHolder.getKey().intValue();
    }


    @Override
    public void deleteWeapon(int weaponId) {
        String sql = "DELETE FROM weapon WHERE id = ?";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, weaponId);
            return ps;
        });
    }

    @Override
    public void deleteArmor(int armorId) {
        String sql = "DELETE FROM armor WHERE id = ?";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, armorId);
            return ps;
        });
    }

    @Override
    public Weapon updateWeaponAttributes(Weapon weapon) {
        String sql = """
                UPDATE weapon
                SET
                    name = CASE WHEN :name IS NOT NULL THEN :name ELSE name END,
                    type = CASE WHEN :type IS NOT NULL THEN :type ELSE type END,
                    weapon_accuracy = CASE WHEN :weapon_accuracy IS NOT NULL THEN :weapon_accuracy ELSE weapon_accuracy END,
                    availability = CASE WHEN :availability IS NOT NULL THEN :availability ELSE availability END,
                    damage = CASE WHEN :damage IS NOT NULL THEN :damage ELSE damage END,
                    reliability = CASE WHEN :reliability IS NOT NULL THEN :reliability ELSE reliability END,
                    hands_required = CASE WHEN :hands_required IS NOT NULL THEN :hands_required ELSE hands_required END,
                    range = CASE WHEN :range IS NOT NULL THEN :range ELSE range END,
                    concealment = CASE WHEN :concealment IS NOT NULL THEN :concealment ELSE concealment END,
                    enhancements = CASE WHEN :enhancements IS NOT NULL THEN :enhancements ELSE enhancements END,
                    weight = CASE WHEN :weight IS NOT NULL THEN :weight ELSE weight END,
                    cost = CASE WHEN :cost IS NOT NULL THEN :cost ELSE cost END,
                    category = CASE WHEN :category IS NOT NULL THEN :category ELSE category END,
                    effect = CASE WHEN :effect IS NOT NULL THEN :effect ELSE effect END
                WHERE id = :id;
                """;
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(weapon);
        jdbcNamed.update(sql, namedParameters);

        return null;
    }

    @Override
    public Armor updateArmorAttributes(Armor armor) {
        String sql = """
            UPDATE armor
            SET
                name = CASE WHEN :name IS NOT NULL THEN :name ELSE name END,
                category = CASE WHEN :category IS NOT NULL THEN :category ELSE category END,
                stopping_power = CASE WHEN :stoppingPower IS NOT NULL THEN :stoppingPower ELSE stopping_power END,
                armor_enhancement = CASE WHEN :armorEnhancement IS NOT NULL THEN :armorEnhancement ELSE armor_enhancement END,
                availability = CASE WHEN :availability IS NOT NULL THEN :availability ELSE availability END,
                effect = CASE WHEN :effect IS NOT NULL THEN :effect ELSE effect END,
                encumbrance_value = CASE WHEN :encumbranceValue IS NOT NULL THEN :encumbranceValue ELSE encumbrance_value END,
                weight = CASE WHEN :weight IS NOT NULL THEN :weight ELSE weight END,
                price = CASE WHEN :price IS NOT NULL THEN :price ELSE price END
            WHERE id = :id;
            """;
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(armor);
        jdbcNamed.update(sql, namedParameters);

        return null;
    }


    @Override
    public String getUserIdByWeaponId( int weaponId) {
        String sql = """ 
                    SELECT user_id from weapon
                        JOIN character ON character.id = weapon.character_id
                        where weapon.id = ?
                    """;
        try {
            return jdbcTemplate.queryForObject(sql, String.class,  weaponId);
        }catch (EmptyResultDataAccessException e) {
           throw e;
        }
    }

    @Override
    public String getUserIdByArmorId(int armorId) {
        String sql = """ 
                    SELECT user_id from armor
                        JOIN character ON character.id = armor.character_id
                        where armor.id = ?
                    """;
        try {
            return jdbcTemplate.queryForObject(sql, String.class,  armorId);
        }catch (EmptyResultDataAccessException e) {
            throw e;
        }
    }
}
