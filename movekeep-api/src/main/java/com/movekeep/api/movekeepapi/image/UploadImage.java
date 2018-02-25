package com.movekeep.api.movekeepapi.image;

import org.springframework.web.multipart.MultipartFile;

public interface UploadImage {

    String uploadImage(MultipartFile file, String userName);
}
