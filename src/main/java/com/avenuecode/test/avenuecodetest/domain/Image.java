package com.avenuecode.test.avenuecodetest.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Image {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String type;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
