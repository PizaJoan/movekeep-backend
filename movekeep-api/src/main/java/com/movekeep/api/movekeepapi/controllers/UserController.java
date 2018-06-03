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

    private UserManager userManager;

    private UploadImage uploader;

    @Autowired
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    @Autowired
    public void setUploader(UploadImage uploader) {
        this.uploader = uploader;
    }

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
