import { Movement } from './movement';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { MovementsService } from './movements.service';


@Component({
  selector: 'app-movements',
  templateUrl: './movements.component.html',
  styleUrls: ['./movements.component.css']
 // providers: [ RestauranteService ]
})
export class MovementsComponent implements OnInit {

  public movements: Movement[] = [];
  public movement: Movement;
 // restauranteEditado: Restaurante = {id1: 0, name: '', description: '', address: '', rate: 1 };

  constructor(
    private movementsService: MovementsService, 
    private router: Router,
    private route: ActivatedRoute
  ) { }

  get(): void {
    this.movements = null;
    this.movementsService
      .get()
      .then(result => {
          this.movements = result
      });
  }

  getById(id): void {
    this.movementsService
      .getById(id)
      .then(result => {
          this.movement = result
      });
  }

  getByName(name): void {
    console.log('->-1-'+name);
    this.movementsService
      .getByName(name)
      .then(result => {
          this.movement = result
          this.movements = [];
          this.movements.push(this.movement);
          console.log(this.movements);
      });
  }

  delete(movement){
    if (confirm("Are you sure you want to delete " + movement.name + "?")) {
      var index = this.movements.indexOf(movement);
      this.movementsService.delete(movement.id)
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
