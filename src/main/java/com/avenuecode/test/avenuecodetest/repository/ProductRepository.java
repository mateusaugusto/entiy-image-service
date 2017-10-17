package com.avenuecode.test.avenuecodetest.repository;

import com.avenuecode.test.avenuecodetest.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
    
}

