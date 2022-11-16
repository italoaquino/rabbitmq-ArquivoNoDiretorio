package com.produto.produto.resource;

import com.produto.produto.services.ImagemDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping(value = "/api/imagem")
public class ImagemResource {

    @Autowired
    private ImagemDataService service;

    @PostMapping(value = "/upload")
    public ResponseEntity<?> uploadImagem(@RequestParam("image")MultipartFile file) throws IOException {

        String uploadImage = service.uploadImagemToFileSystem(file);
        return new ResponseEntity<>(uploadImage, HttpStatus.ACCEPTED);

    }

    @GetMapping(value = "{fileName}")
    public ResponseEntity<?> getImagem(@PathVariable String fileName) throws IOException {

        byte[] file = service.findFile(fileName);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(file);

    }
}


