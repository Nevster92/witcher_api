package com.witcher.witcher_api.repository;

import com.witcher.witcher_api.model.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public class UserRepositoryJdbcImpl  implements UserRepository{
    @Override
    public User getUserById(String userId) {
        return null;
    }

    @Override
    public void setUsernameById(String userId, String userName) {

    }

    @Override
    public void setEmailById(String userId, String email) {

    }
//
//    private JdbcTemplate jdbc;
//
//    @Autowired
//    public UserRepositoryJdbcImpl(JdbcTemplate jdbc){
//        this.jdbc = jdbc;
//    }
//
//
//    @Override
//    public User getUserById(String userId) {
//        String sql = "SELECT * FROM user_data WHERE id = ?";
//            try {
//                return jdbc.queryForObject(sql, new Object[]{userId}, (rs, i) -> new User(
//                        rs.getString("id"),
//                        rs.getString("username"),
//                        rs.getString("email")
//                ));
//            } catch (EmptyResultDataAccessException e) {
//                throw e;
//            }
//
//    }
//
//    @Override
//    public void setUsernameById(String userId, String userName) {
//        String sql = "UPDATE user_data SET username = ? WHERE id= ?";
//        try {
//            jdbc.update(sql, userId, userName);
//        }catch (Exception e){
//
//        }
//    }
//
//    @Override
//    public void setEmailById(String UserId, String email) {
//
//    }

}
