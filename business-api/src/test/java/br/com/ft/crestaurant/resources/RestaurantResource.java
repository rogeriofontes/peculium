/*package br.com.ft.crestaurant.resources;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.ft.crestaurant.services.RestaurantService;
import br.com.ft.crestaurant.web.to.RestaurantTO;


@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantResource.class)
public class RestaurantResource {

	@Autowired
    private MockMvc mvc;
 
    @MockBean
    private RestaurantService service;
    
    //@Test
    public void givenEmployees_whenGetRestaurantTypes_thenReturnStatus()
      throws Exception {
         
    	RestaurantTO restaurant = new RestaurantTO();
    	restaurant.setName("Gran china");
     
        List<RestaurantTO> allRestaurants = Arrays.asList(restaurant);
     
        when(service.getAll()).thenReturn(allRestaurants);
     
        mvc.perform(get("/restaurants")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk());
    }
}
*/