package com.produto.produto.repositories;

import com.produto.produto.entities.ImagemData;
import com.produto.produto.services.ImagemDataService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagemDataRepository extends JpaRepository<ImagemData, Long> {

    ImagemData findByName(String name);

}
