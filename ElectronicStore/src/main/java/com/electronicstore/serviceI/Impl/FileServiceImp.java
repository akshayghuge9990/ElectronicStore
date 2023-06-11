package com.electronicstore.serviceI.Impl;

import com.electronicstore.exception.BadApiRequestException;
import com.electronicstore.serviceI.FileServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImp implements FileServiceI {

    private static final Logger log = LoggerFactory.getLogger(FileServiceImp.class);

    /*
     * @author Akshay
     *
     * @apiNote this method is FileServiceImp of uploadFile
     *
     * @return String
     */
    @Override
    public String uploadFile(MultipartFile file, String path) throws IOException {

        String originalFilename = file.getOriginalFilename();
        log.info("Start the uploadFile in UserServiceImpl: {}", file, path);
        String filename = UUID.randomUUID().toString();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileNameWithExtension = filename + extension;
        String fullPathWithFileName = path  + fileNameWithExtension;

        log.info("full image path: {}",file,path);

        if (extension.equalsIgnoreCase(".png") || extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".jpeg")) {


            //file save
            log.info("file extension is :{}",file,path);
            File folder = new File(path);

            if (!folder.exists()) {

                //create the folder

                folder.mkdirs();


            }

            //upload

            Files.copy(file.getInputStream(), Paths.get(fullPathWithFileName));
            return fileNameWithExtension;


        } else {
            throw new BadApiRequestException("File with this " + extension + "not allowed !!");
        }


    }

    @Override
    public InputStream getResource(String path, String name) throws FileNotFoundException {

        String fullPath = path+File.separator+name;

        InputStream inputStream = new FileInputStream(fullPath);

        return inputStream;
    }
}
