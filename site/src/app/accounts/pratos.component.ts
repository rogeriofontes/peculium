import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { PratoService } from './prato.service';
import { Prato } from './prato';

@Component({
  selector: 'app-pratos',
  templateUrl: './pratos.component.html',
  styleUrls: ['./pratos.component.css']
})
export class PratosComponent implements OnInit {

  public pratos: Prato[] = [];
  public prato: Prato;

  constructor(
    private pratoService: PratoService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

 get(): void {
    this.pratos = null;
    this.pratoService
      .get()
      .then(result => {
          this.pratos = result
      });
  }

  getById(id): void {
    this.pratoService
      .getById(id)
      .then(result => {
          this.prato = result
      });
  }

  getByName(name): void {
    console.log('->-1-'+name);
    this.pratoService
      .getByName(name)
      .then(result => {
          this.prato = result
          this.pratos = [];
          this.pratos.push(this.prato);
          console.log(this.prato);
      });
  }

  delete(prato){
    if (confirm("Are you sure you want to delete " + prato.name + "?")) {
      var index = this.pratos.indexOf(prato);
      this.pratoService.delete(prato.id)
         .then(result => {
           this.get();
            //this.pratos.push(prato);
            //window.location.reload();
           // this.router.navigate(['restaurants-types?refresh=1']);
          });
    }
  }

  add(prato: Prato): void {
    this.pratoService.post(prato)
      .then(restaurante => {
        this.pratos.push(restaurante);
      });
  }

  ngOnInit() {
      //chama o metodo GET do servico
    this.get();
  }

}
