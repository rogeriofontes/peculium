import { Component, OnInit } from '@angular/core';
import { MovementsService } from '../movements/movements.service';
import { Movement } from '../movements/movement';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  title = 'Sistema de Cadastro de Restaurante (Avaliação)';
  urlImagem = 'http://lorempixel.com/640/480/food/';

  public movements: Movement[] = [];

  constructor(
    private movementsService: MovementsService
  ) { }

  getRestaurante(): void {
     this.movements = null; 
     this.movementsService
      .get()
      .then(result => {
          this.movements = result
           console.log(this.movements);
      });
  }

  ngOnInit() {
    this.getRestaurante();
  }

}
