import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';

import { RestauranteService } from '../restaurantes.service';
import { RestaurantsTypesService } from '../../restaurants-types/restaurants-types.service';

import { Restaurante } from '../restaurante';
import { RestaurantType } from '../../restaurants-types/restaurant-type';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-restaurant-form',
  templateUrl: './restaurant-form.component.html',
  styleUrls: ['./restaurant-form.component.css']
})
export class RestaurantFormComponent implements OnInit {

  public myForm: FormGroup; // our model driven form
  public restaurants: Restaurante[] = [];
  public restaurantTypes: RestaurantType[] = [];

  //restauranteEditado: Restaurante = {id1: 0, name: '', description: '', address: '', rate: 1 };
  restaurante: Restaurante = new Restaurante();
  title: string;

  constructor(
    private restauranteService: RestauranteService, 
    private restaurantTypeService: RestaurantsTypesService, 
    private router: Router, 
    private route: ActivatedRoute,
    private formBuilder: FormBuilder) { 
      this.myForm = this.formBuilder.group({
        name: '',
        //description: '',
       // address: '',
      //  rate: '',
        /*address: this.formBuilder.group({
          street: '',
          zip: '',
          city: ''
        })*/
      });
    }

  getRestauranteType(): void {
     this.restaurantTypes = null; 
     this.restaurantTypeService
      .get()
      .then(result => {
          this.restaurantTypes = result
           console.log(this.restaurantTypes);
      });
  }

  ngOnInit() {
     this.getRestauranteType();
     //console.log(this.restaurantTypes);
     var id = this.route.params.subscribe(params => {
      var id = params['id'];

    this.title = id ? 'Edit User' : 'New User';

      if (!id)
        return;

      this.restauranteService.getById(id)
        .then(
          restaurante => this.restaurante = restaurante,
          response => {
            if (response.status == 404) {
              this.router.navigate(['NotFound']);
            }
          });
    });

     
  }


  onSubmit(): void {
    console.log(this.restaurante);
    if (this.restaurante.id){
        this.restauranteService.update(this.restaurante)
          .then(restaurante => {
            this.restaurants = null;
            //this.restaurants.push(restaurante);
            this.router.navigate(['restaurantes']);
        });
        //this.restauranteEditado = { id1: 0, name: '', description: '', address: '', rate: 1 };
    } else {
        this.restauranteService.post(this.restaurante)
          .then(restaurante => {
            this.restaurants = null;
           // this.restaurants.push(restaurante);
            this.router.navigate(['restaurantes']);
        });
    }
  }

}
