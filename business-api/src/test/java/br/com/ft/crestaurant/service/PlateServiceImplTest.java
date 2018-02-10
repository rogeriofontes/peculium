/*package br.com.ft.crestaurant.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ft.crestaurant.repositories.PlateRepository;
import br.com.ft.crestaurant.services.PlateService;
import br.com.ft.crestaurant.services.impl.PlateServiceImpl;
import br.com.ft.crestaurant.web.to.PlateTO;

@RunWith(SpringRunner.class)
public class PlateServiceImplTest {

	@TestConfiguration
	static class PlateServiceImplTestContextConfiguration {

		@Bean
		public PlateService plateService() {
			return new PlateServiceImpl();
		}
	}

	@Autowired
	private PlateService plateService;

	@MockBean
	private PlateRepository plateRepository;

	// write test cases here

	//@Test
	public void should_find_plate_by_name() {
		PlateTO plate = new PlateTO();
		plate.setName("Chines");
		PlateTO result = plateService.save(plate);
		assertEquals("Chines", result.getName());
	}

}
*/