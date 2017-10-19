package com.avenuecode.test.avenuecodetest.controller;

import com.avenuecode.test.avenuecodetest.AbstractControllerTest;
import com.avenuecode.test.avenuecodetest.domain.Product;
import com.avenuecode.test.avenuecodetest.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerTest extends AbstractControllerTest {

	@Before
	public void setUp() {
		super.setUp();
	}

	@MockBean
	private ProductRepository productRepository;

	@Mock
	private Product product;
	
	@Before
	public void prepare() {
		product = new Product();
		product.setId(1L);
		product.setDescription("description");
		product.setName("name_product");
	}

	@Test
	public void testCreate() throws Exception {

		String uri = "/product/";

		String inputJson = super.mapToJson(product);

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
				content.trim().length() > 0 );
	}


	@Test
	public void testGetAllProducts() throws Exception {
		String uri = "/product";

		MvcResult result = mvc.perform(get(uri)
				.accept(MediaType.APPLICATION_JSON)).andReturn();

		String content = result.getResponse().getContentAsString();

		int status = result.getResponse().getStatus();

		Assert.assertEquals("failure - expected HTTP status 200", 200, status);
		Assert.assertTrue(
				"failure - expected HTTP response body to have a value",
				content.trim().length() > 0);
	}

	@Test
	public void testfindProductById() throws Exception {
		String uri = "/product/{id}";

		// Arrange
		when(productRepository.findOne(1L)).thenReturn(product);

		Long id = new Long(1);

		MvcResult result = mvc.perform(get(uri, id)
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();

		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();

		Assert.assertEquals("failure - expected HTTP status 200", 200, status);
		Assert.assertTrue(
				"failure - expected HTTP response body to have a value",
				content.trim().length() > 0);

	}



}
