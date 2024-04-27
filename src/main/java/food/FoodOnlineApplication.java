package food;

import food.domain.Menu;
import food.repository.MenuRepository;
import food.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FoodOnlineApplication implements CommandLineRunner {

	@Autowired
	private MenuRepository menuRepo;

	@Autowired
	private CustomUserService userService;

	public static void main(String[] args) {
		SpringApplication.run(FoodOnlineApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		userService.addUserData();

//		Menu pizzaMenu = new Menu("PZ", "Pizza", Menu.Type.FAST_FOOD);
//		Menu phoMenu = new Menu("PH", "Pho", Menu.Type.ASIAN);
//		Menu macaronMenu = new Menu("MC", "Macaron", Menu.Type.FRENCH);
//		Menu sushiMenu = new Menu("SS", "Sushi", Menu.Type.JAPANESE);
//		Menu ramenMenu = new Menu("RM", "Ramen", Menu.Type.JAPANESE);
//		Menu burgerMenu = new Menu("HB", "Ham Burger", Menu.Type.FAST_FOOD);
//
//		menuRepo.save(pizzaMenu);
//		menuRepo.save(phoMenu);
//		menuRepo.save(macaronMenu);
//		menuRepo.save(sushiMenu);
//		menuRepo.save(ramenMenu);
//		menuRepo.save(burgerMenu);
//
//		System.out.println(pizzaMenu.toString());
	}
}
