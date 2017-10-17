package com.avenuecode.test.avenuecodetest.service;

import com.avenuecode.test.avenuecodetest.domain.Image;

public interface ImageService {

    Image save(Image image);

    Image findById(Long id);

    Image update(Image image);

    void delete(Long id);
}
