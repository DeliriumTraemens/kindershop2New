package org.mykola.kindershop2.repository;

import org.hibernate.annotations.Parameter;
import org.mykola.kindershop2.entity.Category;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
//	List<Category> findByParent(Category top);
	
	@EntityGraph(attributePaths = {"parent"})
	Optional<Category> findByIdAndStatus(Long id, Short s);
	
	List<Category> findByParent(Category top);
	
	@Query("SELECT cat FROM Category cat WHERE cat.id = :id AND cat.status=1")
	@EntityGraph(attributePaths = {"parent.name"})
	Category findCatalogList(@Param("id") Long id);
	
	List<Category> findByNameLike(String name);
	List<Category> findDistinctByNameContaining(String name);

}
