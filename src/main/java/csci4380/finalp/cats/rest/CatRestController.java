/**
 * 
 */
package csci4380.finalp.cats.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
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
import csci4380.finalp.cats.jpa.repo.CatRepository;

@RestController
@RequestMapping("rest/v1/cats")
public class CatRestController {
	@Autowired CatRepository catRepository;


	@RequestMapping("/echoMessage") 
	public String echoMessage(@RequestParam(value="msg", defaultValue="yourName") String message) {
		return "echoMessage echoes: " + message;
	}

	
	@GetMapping("")
	public Page<Cat> findAll(@RequestParam(defaultValue="0") int page, @RequestParam(value="rowsPerPage", defaultValue="3") int size) {
		Page<Cat> catsPage = catRepository.findAll(new PageRequest(page, size));
		return catsPage;
	}

	@GetMapping("/all")
	public  List<Cat> findAll() {
		List<Cat> cats = catRepository.findAll();
		return cats;
	}

	@PostMapping("")
	public  Optional<Cat> save1(@RequestBody final Cat cat) {
		Cat savedCat = catRepository.save(cat);
		return catRepository.findById(savedCat.getPetId());
	}
	
	@PutMapping("")
	public  Optional<Cat> save(@RequestBody final Cat cat) {
		Cat savedCat = catRepository.save(cat);
		return catRepository.findById(savedCat.getPetId());
	}
	
	@GetMapping("/{petId}")
	public  Cat  findByPetId(@PathVariable Integer petId) {
		Cat cat = catRepository.findByPetID(petId);
		return cat;
	}
	
	@DeleteMapping("/{petId}")
	public void delete(@PathVariable Integer petId) {
		catRepository.deleteById(petId);
	}
	
	@GetMapping("/findByNameOrOwnerName/{name}/{ownerName}")
    public List<Cat> findByNameOrOwnerName(@PathVariable("name") String name,@PathVariable("ownerName") String ownerName) {
        List<Cat> cat = catRepository.findByNameOrOwnerName(name, ownerName);
        return cat;
    }
	
	@GetMapping("/byTypeIgnoreCaseQuery/{type}")
    public  Optional<List<Cat>> findByTypeIgnoreCaseQuery(@PathVariable("type") String type) {
        Optional<List<Cat>> cats = catRepository.findByTypeAllIgnoreCase(type);
        return cats;
    }
	
}
