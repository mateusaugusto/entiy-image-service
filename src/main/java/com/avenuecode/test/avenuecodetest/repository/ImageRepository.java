package com.avenuecode.test.avenuecodetest.repository;

import com.avenuecode.test.avenuecodetest.domain.Image;
import com.avenuecode.test.avenuecodetest.dto.ImageDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImageRepository extends CrudRepository<Image, Long> {

    @Query("select new com.avenuecode.test.avenuecodetest.dto.ImageDTO(i.id, i.type) " +
            "from Image i " +
            "where i.product.id = :productId")
    List<ImageDTO> findAllImagesByProductId(@Param("productId") long productId);

}

