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
 * JPA Entity test {@link Category}
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryTest {

	@Autowired
	private TestEntityManager entityManager;
	
	private Category category;
		
	@Before
	public void initValues() throws Exception {		
		Category newCategory = new Category();
		newCategory.setName("Computadores");
		
		category = this.entityManager.persistFlushFind(newCategory);
	} 	
	
	@Test
	public void verifyCategoryValues() throws Exception {
		assertNotNull(category.getId());
		assertNotNull(category.getName());
		assertNotNull(category.getLastUpdate());
		
		assertThat(category.getName()).isEqualTo("Computadores");
	}
}