package com.movekeep.api.movekeepapi.image;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Component
public class ImageHandler implements Upload, Download {

    private final String pathToUpload = "/tmp/users/";

    @Override
    public boolean uploadImage(MultipartFile image, String userName) {
        if (image.isEmpty()) return false;

        try {

            String pathForUser = this.pathToUpload + userName;

            File imageUploaded = new File(pathForUser);

            if (imageUploaded.exists()) deleteFiles(imageUploaded);

            imageUploaded.mkdirs();

            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(pathForUser + "/" + image.getOriginalFilename())));

            byte[] imageBytes = image.getBytes();
            stream.write(imageBytes);
            stream.close();

            return true;

        } catch (Exception e) {

            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean serveImage() {
        return false;
    }

    private void deleteFiles(File path) {

        for (String file : path.list()) {

            File insideFile = new File(path.getAbsolutePath(), file);
            insideFile.delete();
        }

        path.delete();
    }
}
