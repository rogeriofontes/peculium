import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { AccountsService } from './accounts.service';
import { Account } from './account';

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.scss']
})
export class AccountsComponent implements OnInit {
  public accounts: Account[] = [];
  public account: Account;

  constructor( 
    private accountsService: AccountsService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit() {
    this.accounts = [];
    this.get();
  }

  get(): void {
    this.accounts = null; 
    this.accountsService
      .get()
      .then(result => {
          this.accounts = result
      });
  }

  getById(id): void {
    this.accountsService
      .getById(id)
      .then(result => {
          this.account = result
      });
  }

  getByName(name): void {
    console.log('->--'+name);
    this.accountsService
      .getByName(name)
      .then(result => {
          this.account = result
          this.accounts = [];
          this.accounts.push(this.account);
          console.log(this.accounts);
      });
  }

   delete(account){
    if (confirm("Are you sure you want to delete " + account.name + "?")) {
      var index = this.accounts.indexOf(account);
      this.accountsService.delete(account.id)
         .then(result => {
           this.get();
            //this.restaurantTypes.push(restaurantType);
            //window.location.reload();
           // this.router.navigate(['restaurants-types?refresh=1']);
          });
    }
  }

}
