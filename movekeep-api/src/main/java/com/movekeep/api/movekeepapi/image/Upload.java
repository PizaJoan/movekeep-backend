package com.movekeep.api.movekeepapi.image;

import org.springframework.web.multipart.MultipartFile;

public interface Upload {

    boolean uploadImage(MultipartFile file, String userName);
}
