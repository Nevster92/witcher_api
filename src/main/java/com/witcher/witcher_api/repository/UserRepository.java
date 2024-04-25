package com.witcher.witcher_api.repository;

import com.witcher.witcher_api.model.pojo.User;

public interface UserRepository {


    public User getUserById(String userId);


    void updateUser(String userId, User user);
}
