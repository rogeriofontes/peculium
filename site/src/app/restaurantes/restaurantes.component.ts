import { RestaurantType } from './../restaurants-types/restaurant-type';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { RestauranteService } from './restaurantes.service';
import { Restaurante } from './restaurante';

@Component({
  selector: 'app-restaurantes',
  templateUrl: './restaurantes.component.html',
  styleUrls: ['./restaurantes.component.css']
 // providers: [ RestauranteService ]
})
export class RestaurantesComponent implements OnInit {

  public restaurants: Restaurante[] = [];
  public restaurant: Restaurante;
 // restauranteEditado: Restaurante = {id1: 0, name: '', description: '', address: '', rate: 1 };

  constructor(
    private restauranteService: RestauranteService, 
    private router: Router,
    private route: ActivatedRoute
  ) { }

  get(): void {
    this.restaurants = null;
    this.restauranteService
      .get()
      .then(result => {
          this.restaurants = result
      });
  }

  getById(id): void {
    this.restauranteService
      .getById(id)
      .then(result => {
          this.restaurant = result
      });
  }

  getByName(name): void {
    console.log('->-1-'+name);
    this.restauranteService
      .getByName(name)
      .then(result => {
          this.restaurant = result
          this.restaurants = [];
          this.restaurants.push(this.restaurant);
          console.log(this.restaurants);
      });
  }

  delete(restaurant){
    if (confirm("Are you sure you want to delete " + restaurant.name + "?")) {
      var index = this.restaurants.indexOf(restaurant);
      this.restauranteService.delete(restaurant.id)
         .then(result => {
           this.get();
            //this.restaurants.push(restaurant);
            //window.location.reload();
           // this.router.navigate(['restaurants-types?refresh=1']);
          });
    }
  }

  ngOnInit() {
    //chama o metodo GET do servico
    this.get();
  
  }

}
