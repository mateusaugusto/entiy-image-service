package com.avenuecode.test.avenuecodetest.service.impl;

import com.avenuecode.test.avenuecodetest.domain.Product;
import com.avenuecode.test.avenuecodetest.repository.ProductRepository;
import com.avenuecode.test.avenuecodetest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    private ProductRepository getRepository() {
        return productRepository;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public Product update(Product product) {

        if (product == null || null == product.getId()) {
            throw new NoResultException("Requested entity not found.");
        }

        return this.getRepository().save(this.buildProduct(product));
    }

    @Override
    public void delete(Long id) {

        if (null == id) {
            throw new NoResultException("Requested entity not found.");
        }

        Product product = this.findById(id);
        this.getRepository().delete(product);
    }

    private Product buildProduct(Product product) {
        Product productToUpdate = this.findById(product.getId());
        productToUpdate.setDescription(product.getDescription());
        return productToUpdate;
    }
}
