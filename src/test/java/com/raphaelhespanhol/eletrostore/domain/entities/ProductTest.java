package com.raphaelhespanhol.eletrostore.domain.entities;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;

/*
 * @author Raphael Hespanhol (raphael.hespanhol@gmail.com)
 * JPA Entity test {@link Product}
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductTest {

	@Autowired
	private TestEntityManager entityManager;
	
	private Product product;
		
	@Before
	public void initValues() throws Exception {		
		Category newCategory = new Category();
		newCategory.setName("Computadores");
		newCategory = this.entityManager.persistFlushFind(newCategory);
		
		Product newProduct = new Product();
		newProduct.setName("Processador A100");
		newProduct.setPrice(500.05);
		newProduct.setCategory(newCategory);
		
		product = this.entityManager.persistFlushFind(newProduct);
	} 	
	
	@Test
	public void verifyProductValues() throws Exception {
		assertNotNull(product.getId());
		assertNotNull(product.getName());
		assertNotNull(product.getCategory());
		assertNotNull(product.getPrice());
		assertNotNull(product.getLastUpdate());
		
		assertThat(product.getName()).isEqualTo("Processador A100");
	}
}