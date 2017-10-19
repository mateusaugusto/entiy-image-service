package com.avenuecode.test.avenuecodetest.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(exclude = {"product",})
@ToString(exclude = {"product"})
public class Image {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String type;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "product_id")
    private Product product;

}
