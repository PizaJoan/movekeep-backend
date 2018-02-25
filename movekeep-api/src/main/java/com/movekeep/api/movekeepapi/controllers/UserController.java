package com.movekeep.api.movekeepapi.controllers;

import com.movekeep.api.movekeepapi.image.Upload;
import com.movekeep.api.movekeepapi.model.entity.User;
import com.movekeep.api.movekeepapi.model.repomanager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UserController {

    @Autowired
    private UserManager userManager;

    @Autowired
    private Upload uploader;

    @RequestMapping(value = "/addUser", method = RequestMethod.PUT)
    public String addUser(@RequestBody User user) {
        this.userManager.createUser(user);
        return "User added";
    }

    @RequestMapping(value = "/getInfo/{userName}", method = RequestMethod.GET)
    public User getInfo(@PathVariable String userName) {
        return this.userManager.findByUserName(userName);
    }

    @RequestMapping(value = "/imageUpload", method = RequestMethod.POST)
    public String uploadUserImage(@RequestParam("image")MultipartFile image, @RequestParam("username") String userName) {

        uploader.uploadImage(image, userName);

        return "OK";
    }
}
