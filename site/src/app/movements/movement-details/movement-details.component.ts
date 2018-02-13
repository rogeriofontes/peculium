import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { RestauranteService } from '../restaurantes.service';
import { Restaurante } from '../restaurante';

@Component({
  selector: 'app-restaurant-details',
  templateUrl: './restaurant-details.component.html',
  styleUrls: ['./restaurant-details.component.css']
})
export class RestaurantDetailsComponent implements OnInit {
  public restaurant: Restaurante;
  
  constructor(
     private restauranteService: RestauranteService, 
     private router: Router,
     private route: ActivatedRoute,
  ) { }

  ngOnInit() {

     var id = this.route.params.subscribe(params => {
      var id = params['id'];

      if (!id)
        return;

      this.restauranteService.getPlatesById(id)
        .then(
          restaurante => this.restaurant = restaurante,
          response => {
            if (response.status == 404) {
              this.router.navigate(['NotFound']);
            }
          });
    });
  }

}
