package com.avenuecode.test.avenuecodetest.dto;

import com.avenuecode.test.avenuecodetest.domain.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
public class ImageDTO {

    private Long id;
    private String type;
    private Product product;


    public ImageDTO(Long id, String type) {
        this.id = id;
        this.type = type;
    }
}
