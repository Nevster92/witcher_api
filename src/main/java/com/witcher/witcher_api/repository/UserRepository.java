package com.witcher.witcher_api.repository;

import com.witcher.witcher_api.model.pojo.User;

public interface UserRepository {


    public User getUserById(String userId);
    public void setUsernameById(String userId, String userName);
    public void setEmailById(String userId, String email);
}
