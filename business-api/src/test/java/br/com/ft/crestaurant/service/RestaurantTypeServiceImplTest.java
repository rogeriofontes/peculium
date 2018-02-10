/*package br.com.ft.crestaurant.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ft.crestaurant.repositories.RestaurantTypeRepository;
import br.com.ft.crestaurant.services.RestaurantTypeService;
import br.com.ft.crestaurant.services.impl.RestaurantTypeServiceImpl;
import br.com.ft.crestaurant.web.to.RestaurantTypeTO;

@RunWith(SpringRunner.class)
public class RestaurantTypeServiceImplTest {

	@TestConfiguration
	static class RestaurantTypeServiceImplTestContextConfiguration {

		@Bean
		public RestaurantTypeService restaurantTypeService() {
			return new RestaurantTypeServiceImpl();
		}
	}

	@Autowired
	private RestaurantTypeService restaurantTypeService;

	@MockBean
	private RestaurantTypeRepository restaurantTypeRepository;

	// write test cases here

	//@Test
	public void should_find_restaurant_by_name() {
		RestaurantTypeTO restaurantType = new RestaurantTypeTO();
		restaurantType.setName("Chines");
		RestaurantTypeTO result = restaurantTypeService.save(restaurantType);
		assertEquals("Chines", result.getName());
	}

}
*/