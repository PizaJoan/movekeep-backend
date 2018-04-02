package com.movekeep.api.movekeepapi.controllers;

import com.movekeep.api.movekeepapi.image.UploadImage;
import com.movekeep.api.movekeepapi.model.entity.User;
import com.movekeep.api.movekeepapi.model.repomanager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserManager userManager;

    @Autowired
    private UploadImage uploader;

   /* @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@RequestBody User user) {
        this.userManager.createUser(user);
        return "User added";
    }*/

    @RequestMapping(value = "/info/{userName}", method = RequestMethod.GET)
    public User getInfo(@PathVariable String userName) {

        return this.userManager.findByUserName(userName);
    }

    @RequestMapping(value = "/image", method = RequestMethod.PUT)
    public String uploadUserImage(@RequestParam("image")MultipartFile image, @RequestParam("username") String userName) {

        String pathToImage = uploader.uploadImage(image, userName);

        if (null == pathToImage) return "error";

        this.userManager.setUserImage(pathToImage);

        return pathToImage;
    }
}
