/**
 * 
 */
package csci4380.finalp.cats.jpa.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import csci4380.finalp.cats.jpa.model.Cat;


public interface CatRepository extends JpaRepository<Cat, Integer> {
	
	//public Optional<List<Cat>> findByName(String name);
	
	@Query("select c from CAT c where lower(c.name) = lower(:catName)")
	public Cat findByNameIgnoreCaseQuery(@Param("catName") String catName);
	
	@Query("select c from CAT c where c.petId = :petId")
	public Cat findByPetID(@Param("petId")Integer petId);
	
	public List<Cat> findByNameOrOwnerName(String name, String ownerName);
	
	public Optional<List<Cat>> findByTypeAllIgnoreCase(@Param("type") String type);
}

