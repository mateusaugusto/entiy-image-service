package com.avenuecode.test.avenuecodetest.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "product_parent_id")
    private Product parent;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Image> image;

}
