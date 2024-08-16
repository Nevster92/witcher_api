package com.witcher.witcher_api.repository;

import com.witcher.witcher_api.model.pojo.User;
import jakarta.persistence.Persistence;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.Statement;


@Repository
@Component
public class UserRepositoryJdbcImpl  implements UserRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryJdbcImpl( JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getUserById(String userId) {
        return null;
    }


    @Override
    public void updateUser(String userId, User user) {

    }

    public void insertUser(User user) {
        String sql = "insert into user_data  (id, username, email)  values(?,?,?)";

       try {
           jdbcTemplate.update(connection -> {
               PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
               ps.setString(1, user.getId());
               ps.setString(2, user.getUsername());
               ps.setString(3, user.getEmail());
               return ps;
           });
       }catch (BadSqlGrammarException e) {
           System.out.println("Detailed error: " + e.getSQLException());
       }

    }
    public void deleteUser(String userId) {
        String sql = "delete from user_data where id=?";
        jdbcTemplate.update(sql, userId);
    }

//    private JdbcTemplate jdbc;
//
//    @Autowired
//    public UserRepositoryJdbcImpl(JdbcTemplate jdbc){
//        this.jdbc = jdbc;
//    }




}
