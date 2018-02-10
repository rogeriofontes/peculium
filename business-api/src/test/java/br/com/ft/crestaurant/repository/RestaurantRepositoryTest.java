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
import br.com.ft.crestaurant.models.Restaurant;
import br.com.ft.crestaurant.models.RestaurantType;
import br.com.ft.crestaurant.repositories.RestaurantRepository;
import br.com.ft.crestaurant.repositories.RestaurantTypeRepository;

@ContextConfiguration(classes = Application.class, loader = AnnotationConfigContextLoader.class)
@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
@SpringBootTest //@WebMvcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RestaurantRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private RestaurantTypeRepository restaurantTypeRepository;
	
	@Before
	public void getData(){
		
	}

	//@Test
	public void should_find_restaurant_by_name() {
		RestaurantType type = restaurantTypeRepository.findOne(1l);
		Restaurant restaurant = new Restaurant("Lazinho", "Comida Italiana", "rua nana", 1, type);
		restaurant.setCreateBy("Rogério Fontes");
		restaurant.setCreatedDate(new Date());
		entityManager.persist(restaurant);
		Restaurant result = restaurantRepository.findByName("Lazinho");
		assertEquals("Lazinho", result.getName());
	}
	
	//@Test
	public void should_find_restaurant_by_id() {
		RestaurantType type = restaurantTypeRepository.findOne(1l);
		Restaurant restaurant = new Restaurant("Lazinho", "Comida Italiana", "rua nana", 1, type);
		restaurant.setCreateBy("Rogério Fontes");
		restaurant.setCreatedDate(new Date());
		entityManager.persist(restaurant);
		Restaurant result = restaurantRepository.findOne(restaurant.getId());
		assertEquals("Lazinho", result.getName());
	}
	
	//@Test
	public void should_save_restaurant() {
		RestaurantType type = restaurantTypeRepository.findOne(1l);
		Restaurant restaurant = new Restaurant("Lazinho", "Comida Italiana", "rua nana", 1, type);
		restaurant.setCreateBy("Rogério Fontes");
		restaurant.setCreatedDate(new Date());
		entityManager.persist(restaurant);
		Restaurant result = restaurantRepository.findOne(restaurant.getId());
		assertEquals("Lazinho", result.getName());
	}
	
	//@Test
	public void should_update_restaurant() {
		RestaurantType type = restaurantTypeRepository.findOne(1l);
		Restaurant restaurant = new Restaurant("Lazinho", "Comida Italiana", "rua nana", 1, type);
		restaurant.setCreateBy("Rogério Fontes");
		restaurant.setCreatedDate(new Date());
		entityManager.persist(restaurant);
		Restaurant result = restaurantRepository.findByName("Lazinho");
		Restaurant restaurant1 = new Restaurant("Lazinho ALT", "Comida Italiana", "rua nana", 1, type);
		result.update(restaurant1);
		entityManager.persist(result);
		Restaurant result1 = restaurantRepository.findOne(restaurant.getId());
		assertEquals("Lazinho ALT", result1.getName());
	}
	
	//@Test
	public void should_delete_restaurant() {
		RestaurantType type = restaurantTypeRepository.findOne(1l);
		Restaurant restaurant = new Restaurant("Lazinho", "Comida Italiana", "rua nana", 1, type);
		restaurant.setCreateBy("Rogério Fontes");
		restaurant.setCreatedDate(new Date());
		entityManager.persist(restaurant);
		Restaurant result = restaurantRepository.findByName("Lazinho");
		entityManager.remove(result);
		assertEquals(true, result != null);
	}
}
*/