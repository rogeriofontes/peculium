import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { MovementsService } from './movements.service';
import { Movement } from './movement';

@Component({
  selector: 'app-movements',
  templateUrl: './movements.component.html',
  styleUrls: ['./movements.component.scss']
})
export class MovementsComponent implements OnInit {
  public movements: Movement[] = [];
  public movement: Movement;

  constructor( 
    private movementsService: MovementsService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit() {
    this.movements = [];
    this.get();
  }

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
    console.log('->--'+name);
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
            //this.restaurantTypes.push(restaurantType);
            //window.location.reload();
           // this.router.navigate(['restaurants-types?refresh=1']);
          });
    }
  }


}
