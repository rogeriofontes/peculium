/*package br.com.ft.crestaurant.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ft.crestaurant.repositories.RestaurantRepository;
import br.com.ft.crestaurant.services.RestaurantService;
import br.com.ft.crestaurant.services.impl.RestaurantServiceImpl;
import br.com.ft.crestaurant.web.to.RestaurantTO;

@RunWith(SpringRunner.class)
public class RestaurantServiceImplTest {

	@TestConfiguration
	static class RestaurantServiceImplTestContextConfiguration {

		@Bean
		public RestaurantService restaurantService() {
			return new RestaurantServiceImpl();
		}
	}

	@Autowired
	private RestaurantService restaurantService;

	@MockBean
	private RestaurantRepository restaurantRepository;

	// write test cases here

	//@Test
	public void should_find_restaurant_by_name() {
		RestaurantTO restaurant = new RestaurantTO();
		restaurant.setName("Chines");
		RestaurantTO result = restaurantService.save(restaurant);
		assertEquals("Chines", result.getName());
	}

}
*/