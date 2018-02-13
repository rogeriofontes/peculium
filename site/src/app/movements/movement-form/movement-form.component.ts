import { Category } from './../../categorys/category';
import { Movement } from './../movement';

import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';

import { MovementsService } from '../movements.service';
import { CategorysService } from '../../categorys/categorys.service';

import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-movement-form',
  templateUrl: './movement-form.component.html',
  styleUrls: ['./movement-form.component.css']
})
export class MovementFormComponent implements OnInit {

  public myForm: FormGroup; // our model driven form
  public movements: Movement[] = [];
  public categorys: Category[] = [];

  //restauranteEditado: Restaurante = {id1: 0, name: '', description: '', address: '', rate: 1 };
  movement: Movement = new Movement();
  title: string;

  constructor(
    private movementsService: MovementsService, 
    private categoryService: CategorysService, 
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

  getCategory(): void {
     this.movements = null; 
     this.movementsService
      .get()
      .then(result => {
          this.movements = result
           console.log(this.movement);
      });
  }

  ngOnInit() {
     this.getCategory();
     //console.log(this.restaurantTypes);
     var id = this.route.params.subscribe(params => {
      var id = params['id'];

    this.title = id ? 'Edit User' : 'New User';

      if (!id)
        return;

      this.movementsService.getById(id)
        .then(
          movement => this.movement = movement,
          response => {
            if (response.status == 404) {
              this.router.navigate(['NotFound']);
            }
          });
    });

     
  }


  onSubmit(): void {
    console.log(this.movement);
    if (this.movement.id){
        this.movementsService.update(this.movement)
          .then(movement => {
            this.movements = null;
            //this.restaurants.push(restaurante);
            this.router.navigate(['movements']);
        });
        //this.restauranteEditado = { id1: 0, name: '', description: '', address: '', rate: 1 };
    } else {
        this.movementsService.post(this.movement)
          .then(restaurante => {
            this.movements = null;
           // this.restaurants.push(restaurante);
            this.router.navigate(['movements']);
        });
    }
  }

}
