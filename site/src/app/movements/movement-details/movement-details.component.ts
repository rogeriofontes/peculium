import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { MovementsService } from '../movements.service';
import { Movement } from '../movement';

@Component({
  selector: 'app-restaurant-details',
  templateUrl: './restaurant-details.component.html',
  styleUrls: ['./restaurant-details.component.css']
})
export class MovementDetailsComponent implements OnInit {
  public movement: Movement;
  
  constructor(
     private movementsService: MovementsService, 
     private router: Router,
     private route: ActivatedRoute,
  ) { }

  ngOnInit() {

     var id = this.route.params.subscribe(params => {
      var id = params['id'];

      if (!id)
        return;

      this.movementsService.getPlatesById(id)
        .then(
          movement => this.movement = movement,
          response => {
            if (response.status == 404) {
              this.router.navigate(['NotFound']);
            }
          });
    });
  }

}
