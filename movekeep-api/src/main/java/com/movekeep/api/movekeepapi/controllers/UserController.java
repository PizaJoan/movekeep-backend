package com.movekeep.api.movekeepapi.controllers;

import com.movekeep.api.movekeepapi.model.entity.User;
import com.movekeep.api.movekeepapi.model.repomanager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserManager userManager;

    @RequestMapping(value = "/addUser", method = RequestMethod.PUT)
    public String addUser(@RequestBody User user) {
        this.userManager.createUser(user);
        return "User added";
    }

    @RequestMapping(value = "/getInfo/{userName}", method = RequestMethod.GET)
    public User getInfo(@PathVariable String userName) {
        return this.userManager.findByUserName(userName);
    }
}
