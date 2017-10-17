package com.avenuecode.test.avenuecodetest.service.impl;

import com.avenuecode.test.avenuecodetest.domain.Image;
import com.avenuecode.test.avenuecodetest.repository.ImageRepository;
import com.avenuecode.test.avenuecodetest.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    private ImageRepository getRepository() {
        return imageRepository;
    }

    @Override
    public Image save(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public Image findById(Long id) {
        return imageRepository.findOne(id);
    }

    @Override
    public Image update(Image image) {

        if (image == null || null == image.getId()) {
            throw new NoResultException("Requested entity not found.");
        }

        return this.getRepository().save(this.buildImage(image));
    }

    @Override
    public void delete(Long id) {

        if (null == id) {
            throw new NoResultException("Requested entity not found.");
        }

        Image image = this.findById(id);
        this.getRepository().delete(image);
    }

    private Image buildImage(Image image) {
        Image imageToUpdate = this.findById(image.getId());
        return imageToUpdate;
    }
}
