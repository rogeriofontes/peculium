import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';

import { PratoService } from '../prato.service';
import { RestauranteService } from '../../restaurantes/restaurantes.service';

import { Prato } from '../prato';
import { Restaurante } from '../../restaurantes/restaurante';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-plate-form',
  templateUrl: './plate-form.component.html',
  styleUrls: ['./plate-form.component.css']
})
export class PlateFormComponent implements OnInit {

  public myForm: FormGroup; // our model driven form
  public pratos: Prato[] = [];
  public restaurants: Restaurante[] = [];

  prato: Prato = new Prato();
  title: string;

  /* standing data goes here*/
  public onMenus = [
      { value: 'true', display: 'Está no menu' },
      { value: 'false', display: 'Não está no menu' }
  ];

  constructor(
    private pratoService: PratoService, 
    private restauranteService: RestauranteService, 
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

  getRestaurantes(): void {
     this.restaurants = null; 
     this.restauranteService
      .get()
      .then(result => {
          this.restaurants = result
           console.log(this.restaurants);
      });
  }

  ngOnInit() {
    this.getRestaurantes();
     //console.log(this.restaurantTypes);
     var id = this.route.params.subscribe(params => {
      var id = params['id'];

    this.title = id ? 'Edit User' : 'New User';

      if (!id)
        return;

      this.pratoService.getById(id)
        .then(
          prato => this.prato = prato,
          response => {
            if (response.status == 404) {
              this.router.navigate(['NotFound']);
            }
          });
    });
  }

  onSubmit(): void {
    console.log(this.prato);
    if (this.prato.id){
        this.pratoService.update(this.prato)
          .then(prato => {
            this.pratos = null;
            //this.restaurants.push(restaurante);
            this.router.navigate(['pratos']);
        });
        //this.restauranteEditado = { id1: 0, name: '', description: '', address: '', rate: 1 };
    } else {
        this.pratoService.post(this.prato)
          .then(restaurante => {
            this.pratos = null;
           // this.restaurants.push(restaurante);
            this.router.navigate(['pratos']);
        });
    }
  }
}
