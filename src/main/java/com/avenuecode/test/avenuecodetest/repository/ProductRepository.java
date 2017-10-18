package com.avenuecode.test.avenuecodetest.repository;

import com.avenuecode.test.avenuecodetest.domain.Product;
import com.avenuecode.test.avenuecodetest.dto.ProductDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query("select new com.avenuecode.test.avenuecodetest.dto.ProductDTO(p.id, p.name, p.description) from Product p")
    List<ProductDTO> findAllProductsOnly();

    @Query("select distinct new com.avenuecode.test.avenuecodetest.dto.ProductDTO(p.id, p.name, p.description, p.parent) from Product p")
    List<ProductDTO> findAllProductsWithParent();

    @Query("select new com.avenuecode.test.avenuecodetest.dto.ProductDTO(p.id, p.name, p.description) " +
            "from Product p " +
            "where p.id = :id")
    ProductDTO findById(@Param("id") long id);

    @Query("select new com.avenuecode.test.avenuecodetest.dto.ProductDTO(p.id, p.name, p.description, p.parent) " +
            "from Product p " +
            "where p.id = :id")
    ProductDTO findByIdWithParent(@Param("id") long id);

    @Query("select new com.avenuecode.test.avenuecodetest.dto.ProductDTO(p.id, p.name, p.description) " +
            "from Product p " +
            "where p.parent.id = :parent")
    List<ProductDTO> findByChild(@Param("parent") long parent);
    
}

