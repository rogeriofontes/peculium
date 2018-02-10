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

import br.com.ft.crestaurant.services.PlateService;
import br.com.ft.crestaurant.web.to.PlateTO;


@RunWith(SpringRunner.class)
@WebMvcTest(PlateResource.class)
public class PlateResource {

	@Autowired
    private MockMvc mvc;
 
    @MockBean
    private PlateService service;
    
    //@Test
    public void givenPlates_whenGetPlates_thenReturnStatus()
      throws Exception {
         
    	PlateTO plate = new PlateTO();
    	plate.setName("Gran china");
     
        List<PlateTO> allPlates = Arrays.asList(plate);
     
        when(service.getAll()).thenReturn(allPlates);
     
        mvc.perform(get("/plates")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk());
    }
}
*/