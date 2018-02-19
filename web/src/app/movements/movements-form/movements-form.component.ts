import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';

import { MovementsService } from '../movements.service';
import { Movement } from '../movement';
import { Router, ActivatedRoute } from '@angular/router';
import { Category } from '../../categorys/category';
import { Establishment } from '../../establishments/establishment';
import { Account } from '../../accounts/account';
import { CategorysService } from '../../categorys/categorys.service';
import { EstablishmentsService } from '../../establishments/establishments.service';
import { AccountsService } from '../../accounts/accounts.service';

@Component({
  selector: 'app-movements-form',
  templateUrl: './movements-form.component.html',
  styleUrls: ['./movements-form.component.scss']
})
export class MovementsFormComponent implements OnInit {
  public myForm: FormGroup; // our model driven form
  public movements: Movement[] = [];
  public categorys: Category[] = [];
  public accounts: Account[] = [];
  public establishments: Establishment[] = [];
  
  movement: Movement = new Movement();
  title: string;

  constructor(
    private movementsService: MovementsService, 
    private categorysService: CategorysService, 
    private accountsService: AccountsService, 
    private establishmentsService: EstablishmentsService, 
    private router: Router, 
    private route: ActivatedRoute,
    private formBuilder: FormBuilder
  ) { 
    this.myForm = this.formBuilder.group({
      name: '',
    });
  }

  getCategorys(): void {
    this.categorys = null; 
    this.categorysService
     .get()
     .then(result => {
         this.categorys = result
          console.log(this.categorys);
     });
  }

  getAccounts(): void {
    this.accounts = null; 
    this.accountsService
     .get()
     .then(result => {
         this.accounts = result
          console.log(this.accounts);
     });
  }

  getEstablishments(): void {
    this.establishments = null; 
    this.establishmentsService
     .get()
     .then(result => {
         this.establishments = result
          console.log(this.establishments);
     });
  }

  ngOnInit() {
    this.getCategorys();
    this.getAccounts();
    this.getEstablishments();
    var id = this.route.params.subscribe(params => {
      var id = params['id'];

      this.title = id ? 'Edit User' : 'New User';

      if (!id)
        return;

      this.movementsService.getById(id)
        .then(
          category => this.movement = category,
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
          .then(category => {
            this.movements = null;
            this.router.navigate(['movement']);
        });
    } else {
        this.movementsService.post(this.movement)
          .then(restaurantType => {
            this.movements = null;
            this.router.navigate(['movement']);
        });
    }
   
  }

}
