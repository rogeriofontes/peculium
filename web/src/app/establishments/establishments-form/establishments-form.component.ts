import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';

import { EstablishmentsService } from '../establishments.service';
import { LocationService } from './../../addresses/location.service';
import { Establishment } from '../establishment';
import { Location } from '../../addresses/location';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-establishments-form',
  templateUrl: './establishments-form.component.html',
  styleUrls: ['./establishments-form.component.scss']
})
export class EstablishmentsFormComponent implements OnInit {
  public myForm: FormGroup; // our model driven form
  public establishments: Establishment[] = [];
  public locations: Location[] = [];
  
  establishment: Establishment = new Establishment();
  title: string;

  constructor(
    private establishmentsService: EstablishmentsService, 
    private locationService: LocationService, 
    private router: Router, 
    private route: ActivatedRoute,
    private formBuilder: FormBuilder
  ) { 
    this.myForm = this.formBuilder.group({
      name: '',
    });
  }

  getLocations(): void {
    this.locations = null; 
    this.locationService
     .get()
     .then(result => {
         this.locations = result
          console.log(this.locations);
     });
  }

  ngOnInit() {
    this.getLocations();
    var id = this.route.params.subscribe(params => {
      var id = params['id'];

      this.title = id ? 'Edit User' : 'New User';

      if (!id)
        return;

      this.establishmentsService.getById(id)
        .then(
          category => this.establishment = category,
          response => {
            if (response.status == 404) {
              this.router.navigate(['NotFound']);
            }
          });
    });
  }

  onSubmit(): void {
    console.log(this.establishment);
    if (this.establishment.id){
        this.establishmentsService.update(this.establishment)
          .then(category => {
            this.establishments = null;
            this.router.navigate(['establishment']);
        });
    } else {
        this.establishmentsService.post(this.establishment)
          .then(restaurantType => {
            this.establishments = null;
            this.router.navigate(['establishment']);
        });
    }
   
  }
}