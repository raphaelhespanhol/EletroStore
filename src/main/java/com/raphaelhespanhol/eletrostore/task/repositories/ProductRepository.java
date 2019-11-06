package com.raphaelhespanhol.eletrostore.task.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.raphaelhespanhol.eletrostore.domain.entities.Product;

/**
* @author Raphael Hespanhol
* Persistence Layer, here we have the Storage Logic
*/
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	/**
	 * @param String name
	 * return Optional<Product>
	 */
	@Query("SELECT p "
		 + "FROM Product p "
		 + "WHERE p.name = ?1")
	Optional<Product> findAllByName(String name);

	/**
	 * @param Long categoryId
	 * return List<Product>
	 */
	@Query("SELECT p "
		 + "FROM Product p "
		 + "INNER JOIN p.category c "
		 + "WHERE c.id = ?2")
	List<Product> findAllByCategoryId(Long categoryId);
	
	/**
	 * @param String name
	 * @param Long categoryId
	 * return Optional<Product>
	 */
	@Query("SELECT p "
		 + "FROM Product p "
		 + "INNER JOIN p.category c "
		 + "WHERE p.name = ?1 AND c.id = ?2")
	Optional<Product> findByNameAndCategoryId(String name, Long categoryId);
}

