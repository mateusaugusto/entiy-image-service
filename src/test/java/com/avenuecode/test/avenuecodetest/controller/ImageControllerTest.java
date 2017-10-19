package com.avenuecode.test.avenuecodetest.controller;

import com.avenuecode.test.avenuecodetest.AbstractControllerTest;
import com.avenuecode.test.avenuecodetest.domain.Image;
import com.avenuecode.test.avenuecodetest.dto.ImageDTO;
import com.avenuecode.test.avenuecodetest.repository.ImageRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

public class ImageControllerTest extends AbstractControllerTest {

    @Before
    public void setUp() {
        super.setUp();
    }

    @MockBean
    private ImageRepository imageRepository;

    @Mock
    private Image image;

    @Before
    public void prepare() {
        image = new Image();
        image.setId(1L);
        image.setType("description");
    }

    @Test
    public void testCreate() throws Exception {

        String uri = "/image/";

        when(imageRepository.save(image)).thenReturn(image);

        String inputJson = super.mapToJson(image);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);
    }


    @Test
    public void update() throws Exception {
        String uri = "/image/{id}";

        when(imageRepository.save(image)).thenReturn(image);

        MvcResult result = mvc.perform(put(uri)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        String content = result.getResponse().getContentAsString();

        int status = result.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);
    }


}
