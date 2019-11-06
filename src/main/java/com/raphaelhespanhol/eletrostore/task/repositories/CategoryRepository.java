package com.raphaelhespanhol.eletrostore.task.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.raphaelhespanhol.eletrostore.domain.entities.Category;

/**
 * @author Raphael Hespanhol
 * Persistence Layer, here we have the Storage Logic
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	/**
	 * @param String name
	 * return Optional<Category>
	 */
	@Query("SELECT c "
		 + "FROM Category c "
		 + "WHERE c.name = ?1")
	Optional<Category> findByName(String name);
	
}
