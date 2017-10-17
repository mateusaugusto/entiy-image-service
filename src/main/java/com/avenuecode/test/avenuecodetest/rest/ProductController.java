package com.avenuecode.test.avenuecodetest.rest;

import com.avenuecode.test.avenuecodetest.domain.Product;
import com.avenuecode.test.avenuecodetest.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController extends BaseController {

    public static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;


    @ResponseStatus(HttpStatus.CREATED)
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

        return new ResponseEntity<>(this.productService.update(product), HttpStatus.OK);
    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    void delete(@PathVariable Long id) {
        productService.delete(id);
    }

}
