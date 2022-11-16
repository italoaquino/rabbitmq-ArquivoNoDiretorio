package com.produto.produto.services;

import com.produto.produto.entities.ImagemData;
import com.produto.produto.repositories.ImagemDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class ImagemDataService {

    @Autowired
    private ImagemDataRepository repo;

    @Value("${FOLDER_PATH}")
    private String FOLDER_PATH;

    public String uploadImagemToFileSystem(MultipartFile file) throws IOException {

        String pathFile=FOLDER_PATH+file.getOriginalFilename();

        ImagemData imagemData = repo.save(new ImagemData(null, file.getOriginalFilename(), file.getContentType(), pathFile));

        file.transferTo(new File(pathFile));

        if(imagemData != null){
            return "File uploaded successfully : " + pathFile;
        }

        return null;

    }

    public byte[] findFile(String name) throws IOException {

        ImagemData imagemData = repo.findByName(name);
        String pathName = imagemData.getFilePath();

        byte[] images = Files.readAllBytes(new File(pathName).toPath());

        return images;

    }

}


