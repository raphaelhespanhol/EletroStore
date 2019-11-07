package com.raphaelhespanhol.eletrostore.presentation.restcontrollers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
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

import com.raphaelhespanhol.eletrostore.domain.entities.Product;
import com.raphaelhespanhol.eletrostore.task.exceptions.EntityNotFoundException;
import com.raphaelhespanhol.eletrostore.task.services.ProductService;

/**
 * @author RaphaelHespanhol
 * Presentation Layer - Converts and response on XML or JSON format
 */
@RestController
@RequestMapping(value = "api/products")
public class ProductRestController {
	
	private final ProductService service;

	@Autowired
	public ProductRestController(ProductService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<Object> save(@RequestBody Product product) {
		Product objProduct = service.save(product);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					   .buildAndExpand(objProduct.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping
	public List<Product> findAll() {
		return service.findAll();
	}
	
	@GetMapping(path = "{id}")
	public Product findById(@PathVariable("id") Long id) {
		Optional<Product> objProduct = service.findById(id);
		
		if(!objProduct.isPresent())
			throw new EntityNotFoundException("Not found the record id: " + id);
		
		return objProduct.get();
	}
	
	@PutMapping(path = "{id}")
	public ResponseEntity<Object> save(@PathVariable("id") Long id,
									   @Valid @NonNull 
									   @RequestBody Product product) {

		Optional<Product> optProduct = service.findById(id);

		if (!optProduct.isPresent())
			return ResponseEntity.notFound().build();

		service.save(product);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(path = "{id}")
	public void deleteById(@PathVariable("id") Long id) {
		service.deleteById(id);
	}
	
	/*************************CUSTOM CALLS*************************/
	@GetMapping(path = "/findByName", params = {"name"})
	public Product findByName(@RequestParam(value = "name") String name) {
		Optional<Product> optProduct = service.findByName(name);
		
		if(null == optProduct)
			throw new EntityNotFoundException("Not found the product by name ".concat(name));
		
		return optProduct.get();
	}
	
	@GetMapping(path = "/findAllByCategoryId", params = {"categoryId"})
	public List<Product> findAllByCategoryId(@RequestParam(value = "categoryId") Long categoryId) {
		List<Product> products = service.findAllByCategoryId(categoryId);
		
		if(null == products)
			throw new EntityNotFoundException("Not found the product by category id " + categoryId);
		
		return products;
	}
	
	@GetMapping(path = "/findByNameAndCategoryId", params = {"name","categoryId"})
	public Product findByNameAndCategoryId(@RequestParam(value = "name") String name,
										   @RequestParam(value = "categoryId") Long categoryId) {
		Optional<Product> optProduct = service.findByNameAndCategoryId(name, categoryId);
		
		if(null == optProduct)
			throw new EntityNotFoundException("Not found the product by " + name + " and category id " + categoryId);
		
		return optProduct.get();
	}
}
