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
    public void updateUser(String userId, User user) {

    }

//    private JdbcTemplate jdbc;
//
//    @Autowired
//    public UserRepositoryJdbcImpl(JdbcTemplate jdbc){
//        this.jdbc = jdbc;
//    }




}
