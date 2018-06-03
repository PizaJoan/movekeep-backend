package com.movekeep.api.movekeepapi.model.repomanager;

import com.movekeep.api.movekeepapi.model.entity.User;
import com.movekeep.api.movekeepapi.model.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserManager {

    private UserRepo userRepo;

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> getAll() {
        return (List<User>) this.userRepo.findAll();
    }

    public void createUser(User user) {
        this.userRepo.save(user);
    }


    public User findByUserName(String  userName) {
        return this.userRepo.findByUserName(userName);
    }

    public void setUserImage(String pathToImage) {

        String userName = pathToImage.split("/")[2];

        User userToAddImage = this.userRepo.findByUserName(userName);
        userToAddImage.setPathToImage(pathToImage);

        this.userRepo.save(userToAddImage);
    }
}
