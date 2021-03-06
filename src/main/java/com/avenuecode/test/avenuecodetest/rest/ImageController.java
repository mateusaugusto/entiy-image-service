package com.avenuecode.test.avenuecodetest.rest;

import com.avenuecode.test.avenuecodetest.domain.Image;
import com.avenuecode.test.avenuecodetest.dto.ImageDTO;
import com.avenuecode.test.avenuecodetest.dto.ProductDTO;
import com.avenuecode.test.avenuecodetest.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/image")
public class ImageController extends BaseController {

    public static final Logger logger = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    private ImageService imageService;


    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> create(@RequestBody Image image) {
        return new ResponseEntity<>(imageService.save(image), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity<?> updatePessoa(@PathVariable("id") Long id, @RequestBody Image image) {

        if (id == null) {
            logger.error("Unable to update. Product with id {} not found.", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        image.setId(id);

        return new ResponseEntity<>(this.imageService.update(image), HttpStatus.OK);
    }

    @RequestMapping(value = "/all/product/{id}", method = RequestMethod.GET)
    ResponseEntity<List<?>> getAllImagesByProductId(@PathVariable("id") Long id) {

        List<ImageDTO> listImages = imageService.findAllByProductId(id);

        if (listImages.isEmpty())
            return new ResponseEntity<>(listImages, HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(listImages, HttpStatus.OK);
    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    void delete(@PathVariable Long id) {
        imageService.delete(id);
    }

}
