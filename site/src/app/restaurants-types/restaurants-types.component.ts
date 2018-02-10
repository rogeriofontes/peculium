import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { RestaurantsTypesService } from './restaurants-types.service';
import { RestaurantType } from './restaurant-type';

@Component({
  selector: 'app-restaurants-types',
  templateUrl: './restaurants-types.component.html',
  styleUrls: ['./restaurants-types.component.css']
})
export class RestaurantsTypesComponent implements OnInit {

  public restaurantTypes: RestaurantType[] = [];
  public restaurantType: RestaurantType;
  
  constructor(
    private restaurantTypeService: RestaurantsTypesService,
    private router: Router,
    private route: ActivatedRoute,
    ) { }

  get(): void {
    this.restaurantTypes = null; 
    this.restaurantTypeService
      .get()
      .then(result => {
          this.restaurantTypes = result
      });
  }

  getById(id): void {
    this.restaurantTypeService
      .getById(id)
      .then(result => {
          this.restaurantType = result
      });
  }

  getByName(name): void {
    console.log('->--'+name);
    this.restaurantTypeService
      .getByName(name)
      .then(result => {
          this.restaurantType = result
          this.restaurantTypes = [];
          this.restaurantTypes.push(this.restaurantType);
          console.log(this.restaurantTypes);
      });
  }

   delete(restaurantType){
    if (confirm("Are you sure you want to delete " + restaurantType.name + "?")) {
      var index = this.restaurantTypes.indexOf(restaurantType);
      this.restaurantTypeService.delete(restaurantType.id)
         .then(result => {
           this.get();
            //this.restaurantTypes.push(restaurantType);
            //window.location.reload();
           // this.router.navigate(['restaurants-types?refresh=1']);
          });
    }
  }

  ngOnInit() {
     this.restaurantTypes = [];
     this.get();
  }

}