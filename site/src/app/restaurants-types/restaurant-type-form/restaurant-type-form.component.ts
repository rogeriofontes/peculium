import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';

import { RestaurantsTypesService } from '../restaurants-types.service';
import { RestaurantType } from '../restaurant-type';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-restaurant-type-form',
  templateUrl: './restaurant-type-form.component.html',
  styleUrls: ['./restaurant-type-form.component.css']
})
export class RestaurantTypeFormComponent implements OnInit {
  
  public myForm: FormGroup; // our model driven form
  public restaurantTypes: RestaurantType[] = [];
  //restauranteEditado: Restaurante = {id1: 0, name: '', description: '', address: '', rate: 1 };
  restaurantType: RestaurantType = new RestaurantType();
  title: string;

  constructor(
    private restaurantsTypesService: RestaurantsTypesService, 
    private router: Router, 
    private route: ActivatedRoute,
    private formBuilder: FormBuilder) { 
    this.myForm = this.formBuilder.group({
      name: '',
    });
  }

  ngOnInit() {
    var id = this.route.params.subscribe(params => {
      var id = params['id'];

    this.title = id ? 'Edit User' : 'New User';

      if (!id)
        return;

      this.restaurantsTypesService.getById(id)
        .then(
          restaurantType => this.restaurantType = restaurantType,
          response => {
            if (response.status == 404) {
              this.router.navigate(['NotFound']);
            }
          });
    });
  }

  onSubmit(): void {
    console.log(this.restaurantType);
    if (this.restaurantType.id){
        this.restaurantsTypesService.update(this.restaurantType)
          .then(restaurantType => {
            this.restaurantTypes = null;
           // this.restaurantTypes.push(restaurantType);
            this.router.navigate(['restaurants-types']);
        });
        //this.restauranteEditado = { id1: 0, name: '', description: '', address: '', rate: 1 };
    } else {
        this.restaurantsTypesService.post(this.restaurantType)
          .then(restaurantType => {
            this.restaurantTypes = null;
            //this.restaurantTypes.push(restaurantType);
            this.router.navigate(['restaurants-types']);
        });
    }
   
  }
}

