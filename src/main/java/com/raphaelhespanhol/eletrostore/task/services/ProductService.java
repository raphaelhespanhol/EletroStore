package com.raphaelhespanhol.eletrostore.task.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raphaelhespanhol.eletrostore.domain.entities.Category;
import com.raphaelhespanhol.eletrostore.domain.entities.Product;
import com.raphaelhespanhol.eletrostore.task.repositories.ProductRepository;
import com.raphaelhespanhol.eletrostore.task.utils.StringUtil;

@Service
public class ProductService {

	@Autowired
	private final ProductRepository repository;
	
	@Autowired
	private CategoryService categoryService;
	
	public ProductService(ProductRepository repository) {
		this.repository=repository;
	}
	
	public List<Product> findAll() {
		return repository.findAll();
	}
	
	public Optional<Product> findById(Long id) {
		return repository.findById(id);
	}
	
	public List<Object> findAllWithoutImages(){
		return repository.findAllWithoutImages();
	}
	
	public List<Object> findByName(String name) {
		return repository.findAllByName(name);
	}
	
	public List<Object> findAllByCategoryId(Long categoryId) {
		return repository.findAllByCategoryId(categoryId);
	}
	
	public List<Object> findByNameAndCategoryId(String name, Long categoryId) {
		return repository.findByNameAndCategoryId(name, categoryId);
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
	public Product save(Product product) {
		//Verify if name field is not null
		if (StringUtil.isEmpty(product.getName()) || null == product.getCategory())
			return null;
		
		if (null == product.getCategory().getId() ||
			!StringUtil.isEmpty(product.getCategory().getName())) {
			Optional<Category> optCategory = categoryService.findByName(product.getCategory().getName());
			//Verify if exists, returning the value or add a new one
			product.setCategory((optCategory.isPresent())?optCategory.get():categoryService.save(product.getCategory()));	
		}
		
		//Always updating the date
		product.setLastUpdate(new Date());
		
		return repository.saveAndFlush(product);
	}
	
	
	
	
}
