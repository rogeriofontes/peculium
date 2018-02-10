/*package br.com.ft.crestaurant.repository;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import br.com.ft.crestaurant.Application;
import br.com.ft.crestaurant.models.Plate;
import br.com.ft.crestaurant.models.Restaurant;
import br.com.ft.crestaurant.repositories.PlateRepository;
import br.com.ft.crestaurant.repositories.RestaurantRepository;

@ContextConfiguration(classes = Application.class, loader = AnnotationConfigContextLoader.class)
@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
@SpringBootTest //@WebMvcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PlateRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private PlateRepository restaurantRepository;
	
	@Autowired
	private RestaurantRepository restaurantTypeRepository;
	
	@Before
	public void getData(){
		
	}

	@Test
	public void should_find_restaurant_by_name() {
		Restaurant type = restaurantTypeRepository.findOne(1l);
		Plate restaurant = new Plate("Prato1", "Macaronada Ita", true, type);
		restaurant.setCreateBy("Rogério Fontes");
		restaurant.setCreatedDate(new Date());
		entityManager.persist(restaurant);
		Plate result = restaurantRepository.findByName("Prato1");
		assertEquals("Prato1", result.getName());
	}
	
	//@Test
	public void should_find_restaurant_by_id() {
		Restaurant type = restaurantTypeRepository.findOne(1l);
		Plate restaurant = new Plate("Prato1", "Macaronada Ita", true, type);
		restaurant.setCreateBy("Rogério Fontes");
		restaurant.setCreatedDate(new Date());
		entityManager.persist(restaurant);
		Plate result = restaurantRepository.findOne(restaurant.getId());
		assertEquals("Prato1", result.getName());
	}
	
	//@Test
	public void should_save_restaurant() {
		Restaurant type = restaurantTypeRepository.findOne(1l);
		Plate restaurant = new Plate("Prato1", "Macaronada Ita", true, type);
		restaurant.setCreateBy("Rogério Fontes");
		restaurant.setCreatedDate(new Date());
		entityManager.persist(restaurant);
		Plate result = restaurantRepository.findOne(restaurant.getId());
		assertEquals("Prato1", result.getName());
	}
	
	//@Test
	public void should_update_restaurant() {
		Restaurant type = restaurantTypeRepository.findOne(1l);
		Plate restaurant = new Plate("Prato1", "Macaronada Ita", true, type);
		restaurant.setCreateBy("Rogério Fontes");
		restaurant.setCreatedDate(new Date());
		entityManager.persist(restaurant);
		Plate result = restaurantRepository.findByName("Prato1");
		Plate restaurant1 = new Plate("Prato1 ALT", "Macaronada Ita", true, type);
		result.update(restaurant1);
		entityManager.persist(result);
		Plate result1 = restaurantRepository.findOne(restaurant.getId());
		assertEquals("Prato1 ALT", result1.getName());
	}
	
	//@Test
	public void should_delete_restaurant() {
		Restaurant type = restaurantTypeRepository.findOne(1l);
		Plate restaurant = new Plate("Prato1", "Macaronada Ita", true, type);
		restaurant.setCreateBy("Rogério Fontes");
		restaurant.setCreatedDate(new Date());
		entityManager.persist(restaurant);
		Plate result = restaurantRepository.findByName("Prato1");
		entityManager.remove(result);
		assertEquals(true, result != null);
	}
}
*/