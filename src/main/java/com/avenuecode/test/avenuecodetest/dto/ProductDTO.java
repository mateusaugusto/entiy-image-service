package com.avenuecode.test.avenuecodetest.dto;

import com.avenuecode.test.avenuecodetest.domain.Image;
import com.avenuecode.test.avenuecodetest.domain.Product;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private Product parent;
    private Set<Image> image;


    public ProductDTO(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public ProductDTO(Long id, String name, String description, Product parent) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.parent = parent;
    }


}
