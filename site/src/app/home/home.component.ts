import { Component, OnInit } from '@angular/core';
import { RestauranteService } from '../restaurantes/restaurantes.service';
import { Restaurante } from '../restaurantes/restaurante';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  title = 'Sistema de Cadastro de Restaurante (Avaliação)';
  urlImagem = 'http://lorempixel.com/640/480/food/';

  public restaurants: Restaurante[] = [];

  constructor(
    private restauranteService: RestauranteService
  ) { }

  getRestaurante(): void {
     this.restaurants = null; 
     this.restauranteService
      .get()
      .then(result => {
          this.restaurants = result
           console.log(this.restaurants);
      });
  }

  ngOnInit() {
    this.getRestaurante();
  }

}
