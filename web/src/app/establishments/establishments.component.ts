import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { EstablishmentsService } from './establishments.service';
import { Establishment } from './establishment';

@Component({
  selector: 'app-establishments',
  templateUrl: './establishments.component.html',
  styleUrls: ['./establishments.component.scss']
})
export class EstablishmentsComponent implements OnInit {
  public establishments: Establishment[] = [];
  public establishment: Establishment;

  constructor(
    private establishmentsService: EstablishmentsService,
    private router: Router,
    private route: ActivatedRoute) { }

    ngOnInit() {
      this.establishments = [];
      this.get();
    }
  
    get(): void {
      this.establishments = null; 
      this.establishmentsService
        .get()
        .then(result => {
            this.establishments = result
        });
    }
  
    getById(id): void {
      this.establishmentsService
        .getById(id)
        .then(result => {
            this.establishment = result
        });
    }
  
    getByName(name): void {
      console.log('->--'+name);
      this.establishmentsService
        .getByName(name)
        .then(result => {
            this.establishment = result
            this.establishments = [];
            this.establishments.push(this.establishment);
            console.log(this.establishments);
        });
    }
  
     delete(establishment){
      if (confirm("Are you sure you want to delete " + establishment.name + "?")) {
        var index = this.establishments.indexOf(establishment);
        this.establishmentsService.delete(establishment.id)
           .then(result => {
             this.get();
              //this.restaurantTypes.push(restaurantType);
              //window.location.reload();
             // this.router.navigate(['restaurants-types?refresh=1']);
            });
      }
    }

}
