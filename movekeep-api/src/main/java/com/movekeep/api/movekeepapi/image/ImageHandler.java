package com.movekeep.api.movekeepapi.image;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Component
public class ImageHandler implements UploadImage {

    @Value("${movekeep.path.to.images}")
    private String pathToUpload;

    @Override
    public String uploadImage(MultipartFile image, String userName) {
        if (image.isEmpty()) return null;

        try {

            String pathForUser = this.pathToUpload + userName;

            File imageUploaded = new File(pathForUser);

            if (imageUploaded.exists()) deleteFiles(imageUploaded);

            imageUploaded.mkdirs();

            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(pathForUser + "/" + image.getOriginalFilename())));

            byte[] imageBytes = image.getBytes();
            stream.write(imageBytes);
            stream.close();

            return formatUrlToImage(userName, image.getOriginalFilename());

        } catch (Exception e) {

            System.out.println(e.getMessage());
            return null;
        }
    }

    private void deleteFiles(File path) {

        for (String file : path.list()) {

            File insideFile = new File(path.getAbsolutePath(), file);
            insideFile.delete();
        }

        path.delete();
    }

    private String formatUrlToImage(String userName, String imageName) {
        return "/images/" + userName + "/" + imageName;
    }
}
