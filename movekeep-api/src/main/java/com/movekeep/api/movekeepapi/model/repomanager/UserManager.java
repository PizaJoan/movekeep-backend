package com.movekeep.api.movekeepapi.model.repomanager;

import com.movekeep.api.movekeepapi.model.entity.User;
import com.movekeep.api.movekeepapi.model.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserManager {

    @Autowired
    private UserRepo userRepo;


    public void createUser(User user) {
        this.userRepo.save(user);
    }


    public User findByUserName(String  userName) {
        return this.userRepo.findByUserName(userName);
    }
}
