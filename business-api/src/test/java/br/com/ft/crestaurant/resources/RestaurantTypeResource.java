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

import br.com.ft.crestaurant.services.RestaurantTypeService;
import br.com.ft.crestaurant.web.to.RestaurantTypeTO;


@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantTypeResource.class)
public class RestaurantTypeResource {

	@Autowired
    private MockMvc mvc;
 
    @MockBean
    private RestaurantTypeService service;
    
    //@Test
    public void givenEmployees_whenGetRestaurantTypes_thenReturnStatus()
      throws Exception {
         
    	RestaurantTypeTO restaurantType = new RestaurantTypeTO();
    	restaurantType.setName("Chines");
     
        List<RestaurantTypeTO> allRestaurantTypes = Arrays.asList(restaurantType);
     
        when(service.getAll()).thenReturn(allRestaurantTypes);
     
        mvc.perform(get("/restaurant-types")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk());
    }
}
*/