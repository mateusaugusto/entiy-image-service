package com.avenuecode.test.avenuecodetest.rest;

import com.avenuecode.test.avenuecodetest.domain.Product;
import com.avenuecode.test.avenuecodetest.dto.ProductDTO;
import com.avenuecode.test.avenuecodetest.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController extends BaseController {

    public static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;


    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<Product> create(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity<Product> updatePessoa(@PathVariable("id") Long id, @RequestBody Product product) {

        if (id == null) {
            logger.error("Unable to update. Product with id {} not found.", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        product.setId(id);

        return new ResponseEntity<>(this.productService.update(product), HttpStatus.CREATED);
    }


    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<ProductDTO>> getAllProductsOnly() {

        List<ProductDTO> listProducts = productService.findAllProductOnly();

        if (listProducts.isEmpty())
            return new ResponseEntity<>(listProducts, HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(listProducts, HttpStatus.OK);
    }

    @RequestMapping(value = "/all/images", method = RequestMethod.GET)
    ResponseEntity<List<ProductDTO>> getAllProductsWithParent() {

        List<ProductDTO> listProductsImages = productService.findAllProductsWithParent();

        if (listProductsImages.isEmpty())
            return new ResponseEntity<>(listProductsImages, HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(listProductsImages, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<ProductDTO> getProduct(@PathVariable("id") Long id) {
        ProductDTO product = productService.findProductById(id);

        if (null == product)
            return new ResponseEntity<>(product, HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @RequestMapping(value = "/parent/{id}", method = RequestMethod.GET)
    ResponseEntity<ProductDTO> getProductWithParent(@PathVariable("id") Long id) {
        ProductDTO product = productService.findProductByIdWithParent(id);

        if (null == product)
            return new ResponseEntity<>(product, HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @RequestMapping(value = "/child/{id}", method = RequestMethod.GET)
    ResponseEntity<List<ProductDTO>> getAllByChild(@PathVariable("id") Long id) {

        List<ProductDTO> listProducts = productService.findByChild(id);

        if (listProducts.isEmpty())
            return new ResponseEntity<>(listProducts, HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(listProducts, HttpStatus.OK);
    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    void delete(@PathVariable Long id) {
        productService.delete(id);
    }

}
