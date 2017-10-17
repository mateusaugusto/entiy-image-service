package com.avenuecode.test.avenuecodetest.service;

import com.avenuecode.test.avenuecodetest.domain.Product;
import com.avenuecode.test.avenuecodetest.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    Product save(Product product);

    Product findById(Long id);

    Product update(Product product);

    List<ProductDTO> findAllProductOnly();

    List<ProductDTO> findAllProductsWithParent();

    ProductDTO findProductById(Long id);

    ProductDTO findProductByIdWithParent(Long id);

    List<ProductDTO> findByChild(Long id);

    void delete(Long id);
}
