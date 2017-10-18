package com.avenuecode.test.avenuecodetest.service;

import com.avenuecode.test.avenuecodetest.AbstractTest;
import com.avenuecode.test.avenuecodetest.domain.Image;
import com.avenuecode.test.avenuecodetest.domain.Product;
import com.avenuecode.test.avenuecodetest.dto.ProductDTO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class ProductServiceTest extends AbstractTest {

    @Autowired
    private ProductService service;

    @Test
    public void testCreate() {
        Product product = this.buildProduct();
        service.save(product);

        Assert.assertNotNull("failure - expected not null", product);
        Assert.assertNotNull("failure - expected id attribute not null",
                product.getId());
    }

    @Test
    public void testUpdate() {
        Product product = this.buildProduct();
        product = service.save(product);

        product.setName("it was changed");
        product = service.update(product);

        Assert.assertEquals("failure - expected text attribute match", "it was changed",
                product.getName());
    }

    @Test
    public void findAllProductOnly() {
        IntStream.range(0, 10).parallel().forEach(
                nbr -> {
                    Product product = this.buildProduct();
                    service.save(product);
                });

        List<ProductDTO> list = service.findAllProductOnly();

        list.forEach(productDTO -> {
            Assert.assertNull(productDTO.getImage());
        });
    }

    @Test
    public void findAllWithParent() {
        IntStream.range(0, 2)
                .forEach(
                        nbr -> {
                            Product product = this.buildProductParent();
                            product = service.save(product);
                            product.setParent(product);
                            service.update(product);
                        });

        List<ProductDTO> list = service.findAllProductsWithParent();
        list.forEach(productDTO -> Assert.assertNotNull(productDTO.getParent()));
    }

    @Test
    public void findByChild() {
        Product product = this.buildProductParent();
        product = service.save(product);
        product.setParent(product);
        service.update(product);

        List<ProductDTO> list = service.findByChild(product.getId());
        list.forEach(productDTO -> Assert.assertNotNull(productDTO.getId()));

    }

    @Test
    public void delete() {
        Product product = this.buildProduct();
        product = service.save(product);
        service.delete(product.getId());

        List<ProductDTO> list = service.findAllProductOnly();
        Assert.assertEquals(0, list.size());

    }


    public Product buildProduct() {
        Product product = new Product();
        product.setDescription(this.randomName());
        product.setName(this.randomName());
        product.setImage(new HashSet<>(this.buildImages()));
        return product;
    }

    public Product buildProductParent() {
        Product product = this.buildProduct();
        product.setParent(product);
        return product;
    }

    private List<Image> buildImages() {
        List<Image> listImages = new ArrayList<>();
        Image image = new Image();
        image.setType(this.randomName());
        listImages.add(image);
        return listImages;
    }

    public String randomName() {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        return generatedString;
    }

}
