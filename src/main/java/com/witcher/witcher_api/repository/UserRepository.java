package com.witcher.witcher_api.repository;

import com.witcher.witcher_api.model.pojo.User;

public interface UserRepository {


     User getUserById(String userId);


    void updateUser(String userId, User user);
}
