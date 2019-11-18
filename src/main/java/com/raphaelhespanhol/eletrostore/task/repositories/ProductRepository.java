package com.raphaelhespanhol.eletrostore.task.repositories;

import java.util.List;

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
	 * return List<Object>
	 */
	@Query(value="SELECT p.id, p.name, c.name AS category, p.price, p.last_update "
		 + "FROM Product p "
		 + "INNER JOIN Category c ON p.id_category=c.id", nativeQuery=true)
	List<Object> findAllWithoutImages();
	
	/**
	 * @param Long categoryId
	 * return List<Object>
	 */
	@Query(value="SELECT p.id, p.name, c.name AS category, p.price, p.last_update "
			 + "FROM Product p "
			 + "INNER JOIN Category c ON p.id_category=c.id "
			 + "WHERE c.id = ?1", nativeQuery=true)
	List<Object> findAllByCategoryId(Long categoryId);
	
	/**
	 * @param String name
	 * return List<Object>
	 */
	@Query(value="SELECT p.id, p.name, c.name AS category, p.price, p.last_update "
			 + "FROM Product p "
			 + "INNER JOIN Category c ON p.id_category=c.id "
			 + "WHERE p.name LIKE %?1%", nativeQuery=true)
	List<Object> findAllByName(String name);

	/**
	 * @param String name
	 * @param Long categoryId
	 * return List<Object>
	 */
	@Query(value="SELECT p.id, p.name, c.name AS category, p.price, p.last_update "
			 + "FROM Product p "
			 + "INNER JOIN Category c ON p.id_category=c.id "
			 + "WHERE p.name LIKE %?1% AND c.id = ?2", nativeQuery=true)
	List<Object> findAllByNameAndCategoryId(String name, Long categoryId);
}

