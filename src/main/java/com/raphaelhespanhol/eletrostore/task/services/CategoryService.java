package com.raphaelhespanhol.eletrostore.task.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raphaelhespanhol.eletrostore.domain.entities.Category;
import com.raphaelhespanhol.eletrostore.task.repositories.CategoryRepository;
import com.raphaelhespanhol.eletrostore.task.utils.StringUtil;

@Service
public class CategoryService {

	@Autowired
	private final CategoryRepository repository;
	
	public CategoryService(CategoryRepository repository) {
		this.repository=repository;
	}
	
	public List<Category> findAll() {
		return repository.findAll();
	}
	
	public Optional<Category> findById(Long id) {
		return repository.findById(id);
	}
	
	public Optional<Category> findByName(String name) {
		return repository.findByName(name);
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
	public Category save(Category category) {
		//Verify if name field is not null
		if (StringUtil.isEmpty(category.getName()))
			return null;
		
		if (null == category.getId() || category.getId() == 0) {
			Optional<Category> optCategory = this.findByName(category.getName());
			if (optCategory.isPresent())
				category.setId(optCategory.get().getId());
		}
		
		//Always updating the date
		category.setLastUpdate(new Date());
		
		return repository.saveAndFlush(category);
	}
	
	
	
	
}
