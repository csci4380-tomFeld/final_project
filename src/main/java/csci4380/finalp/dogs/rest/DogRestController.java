/**
 * 
 */
package csci4380.finalp.dogs.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import csci4380.finalp.cats.jpa.model.Cat;
import csci4380.finalp.dogs.mongodb.model.Dog;
import csci4380.finalp.dogs.mongodb.repo.DogRepository;;


@RestController
@RequestMapping("rest/v1/dogs")
public class DogRestController {
	private DogRepository dogRepository;
		public DogRestController(DogRepository dogRepository) {
		this.dogRepository = dogRepository;
	}

	@GetMapping("/echoMessage")
	public String echoMessage(@RequestParam(value="msg", defaultValue="Hello") String message) {
		return "echoMessage echoed: " + message;
	}

	@GetMapping("/messageInJsonObject")
	@CrossOrigin(origins={"http://localhost:4200","http://localhost:4201"})
	public String messageInJsonObject(@RequestParam(value="msg", defaultValue="Hello") String message) {
		return "{\"message\":\"messageInJsonObject returned:" + message + "\"}";
	}

	@GetMapping("")
	public Page<Dog> findAll(@RequestParam(defaultValue="0") int page, @RequestParam(value="rowsPerPage", defaultValue="5") int size) {
		Page<Dog> dogsPage = dogRepository.findAll(new PageRequest(page, size));
		return dogsPage;
	}

	@GetMapping("/all")
	public  List<Dog> findAll() {
		List<Dog> dogs = dogRepository.findAll();
		return dogs;
	}

	@GetMapping("/{id}")
	public  Optional<Dog> findById(@PathVariable String id) {
		Optional<Dog> dog = dogRepository.findById(id);
		return dog;
	}

	@GetMapping("/petId/{petId}")
	public  Optional<List<Dog>> findByPetId(@PathVariable String petId) {
		Optional<List<Dog>> dogs = dogRepository.findByPetId(petId);
		return dogs;
	}
	
	@GetMapping("/ageRange/{ageGT}/{ageLT}")
	public  List<Dog> findMyByAgeBetween(@PathVariable Integer ageGT, @PathVariable Integer ageLT) {
		List<Dog> dogs = dogRepository.findMyByAgeBetween(ageGT, ageLT);
		return dogs;
	}

	@PostMapping("")
	public  Optional<Dog> save(@RequestBody final Dog dog) {
		Dog savedDog = dogRepository.save(dog);
		return dogRepository.findById(savedDog.getId());
	}

	@PutMapping("")
	public  Dog insert(@RequestBody final Dog dog) {
		Dog insertedDog = dogRepository.insert(dog);
		return insertedDog;
	}
	
	@DeleteMapping("/{id}")
	public  void delete(@PathVariable("id") String id) {
		dogRepository.deleteById(id);
	}
	
	@DeleteMapping("/petId/{petId}")
	public  void deleteByPetId(@PathVariable String petId) {
		dogRepository.deleteByPetId(petId);
	}
	
	@GetMapping("/findByNameOrOwnerName/{name}/{ownerName}")
    public List<Dog> findByNameOrOwnerName(@PathVariable("name") String name,@PathVariable("ownerName") String ownerName) {
        List<Dog> dog = dogRepository.findByNameOrOwnerName(name, ownerName);
        return dog;
    }
	
	@GetMapping("/byTypeIgnoreCaseQuery/{type}")
    public  Optional<List<Dog>> findByTypeIgnoreCaseQuery(@PathVariable("type") String type) {
        Optional<List<Dog>> dogs = dogRepository.findByTypeAllIgnoreCase(type);
        return dogs;
    }
	
}

