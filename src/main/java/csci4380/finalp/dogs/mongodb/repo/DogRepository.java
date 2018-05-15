package csci4380.finalp.dogs.mongodb.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import csci4380.finalp.cats.jpa.model.Cat;
import csci4380.finalp.dogs.mongodb.model.Dog;;

@Repository
//public interface DogRepository extends MongoRepository<Dog, String>, QuerydslPredicateExecutor<Dog> {
public interface DogRepository extends MongoRepository<Dog, String> {
	public Optional<Dog> findByPetId(@Param("petId")String petId);
	public void deleteByPetId(String petId);
	public List<Dog> findByNameOrOwnerName(String name, String ownerName);
	@Query
	public Optional<List<Dog>> findByTypeAllIgnoreCase(@Param("type") String type);
}
