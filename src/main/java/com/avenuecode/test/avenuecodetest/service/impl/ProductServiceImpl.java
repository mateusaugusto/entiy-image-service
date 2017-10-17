package com.avenuecode.test.avenuecodetest.service.impl;

import com.avenuecode.test.avenuecodetest.domain.Product;
import com.avenuecode.test.avenuecodetest.dto.ProductDTO;
import com.avenuecode.test.avenuecodetest.repository.ProductRepository;
import com.avenuecode.test.avenuecodetest.service.ImageService;
import com.avenuecode.test.avenuecodetest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageService imageService;

    private ProductRepository getRepository() {
        return productRepository;
    }

    @Override
    public Product save(Product product) {

        if(null != product.getImage()){
            product.getImage()
                    .forEach(image -> image.setProduct(product));
        }

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
    public List<ProductDTO> findAllProductOnly(){
        return this.getRepository().findAllProductsOnly();
    }

    @Override
    public List<ProductDTO> findAllProductsWithParent(){
        return this.getRepository().findAllProductsWithParent();
    }

    @Override
    public ProductDTO findProductById(Long id){
        return this.getRepository().findById(id);
    }

    @Override
    public ProductDTO findProductByIdWithParent(Long id){
        return this.getRepository().findById(id);
    }

    @Override
    public List<ProductDTO> findByChild(Long id){
        return this.getRepository().findByChild(id);
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
