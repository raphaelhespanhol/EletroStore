package com.raphaelhespanhol.eletrostore.presentation.restcontrollers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.raphaelhespanhol.eletrostore.domain.entities.Category;
import com.raphaelhespanhol.eletrostore.task.exceptions.EntityNotFoundException;
import com.raphaelhespanhol.eletrostore.task.services.CategoryService;

/**
 * @author RaphaelHespanhol
 * Presentation Layer - Converts and response on XML or JSON format
 */
@RestController
@RequestMapping(value = "api/categories")
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200", "http://localhost:8081" })
public class CategoryRestController {
	
	private final CategoryService service;

	@Autowired
	public CategoryRestController(CategoryService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<Object> save(@RequestBody Category category) {
		Category objCategory = service.save(category);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					   .buildAndExpand(objCategory.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping
	public List<Category> findAll() {
		return service.findAll();
	}
	
	@GetMapping(path = "{id}")
	public Category findById(@PathVariable("id") Long id) {
		Optional<Category> objCategory = service.findById(id);
		
		if(!objCategory.isPresent())
			throw new EntityNotFoundException("Not found the record id: " + id);
		
		return objCategory.get();
	}
	
	@PutMapping(path = "{id}")
	public ResponseEntity<Object> save(@PathVariable("id") Long id,
									   @Valid @NonNull 
									   @RequestBody Category category) {

		Optional<Category> optCategory = service.findById(id);

		if (!optCategory.isPresent())
			return ResponseEntity.notFound().build();

		service.save(category);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(path = "{id}")
	public void deleteById(@PathVariable("id") Long id) {
		service.deleteById(id);
	}
	
	/*************************CUSTOM CALLS*************************/
	@GetMapping(path = "/findByName", params = {"name"})
	public Category findByName(@RequestParam(value = "name") String name) {
		Optional<Category> optCategory = service.findByName(name);
		
		if(null == optCategory)
			throw new EntityNotFoundException("Not found the category: ".concat(name));
		
		return optCategory.get();
	}
}
