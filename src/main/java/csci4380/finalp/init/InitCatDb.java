/**
 * 
 */
package csci4380.finalp.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import csci4380.finalp.cats.jpa.model.Cat;
import csci4380.finalp.cats.jpa.repo.CatRepository;

@Component
public class InitCatDb implements CommandLineRunner {

	private CatRepository catRepository;
	
	@Autowired
	public InitCatDb(CatRepository catRepository) {
		super();
		this.catRepository = catRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		Cat catUno = new Cat("joe", "tabby", "burnie", "18 forester way", 21, true, "05/04/2018");
		Cat savedCatUno = catRepository.save(catUno);
		System.out.println("Saved catUno");
	}

}
