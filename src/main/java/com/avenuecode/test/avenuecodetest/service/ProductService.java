package com.avenuecode.test.avenuecodetest.service;

import com.avenuecode.test.avenuecodetest.domain.Product;

public interface ProductService {

    Product save(Product product);

    Product findById(Long id);

    Product update(Product product);

    void delete(Long id);
}
