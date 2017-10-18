package com.avenuecode.test.avenuecodetest.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(exclude = {"image"})
@ToString(exclude = {"image"})
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "product_parent_id")
    private Product parent;

    @OneToMany(mappedBy = "product", cascade = CascadeType.MERGE)
    @JsonManagedReference
    private Set<Image> image;

}
