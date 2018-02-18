import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';

import { AccountsService } from '../accounts.service';
import { Account } from '../account';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-accounts-form',
  templateUrl: './accounts-form.component.html',
  styleUrls: ['./accounts-form.component.scss']
})
export class AccountsFormComponent implements OnInit {
  public myForm: FormGroup; // our model driven form
  public accounts: Account[] = [];
  
  account: Account = new Account();
  title: string;

  constructor(
    private accountsService: AccountsService, 
    private router: Router, 
    private route: ActivatedRoute,
    private formBuilder: FormBuilder
  ) { 
    this.myForm = this.formBuilder.group({
      name: '',
    });
  }

  ngOnInit() {
    var id = this.route.params.subscribe(params => {
      var id = params['id'];

      this.title = id ? 'Edit User' : 'New User';

      if (!id)
        return;

      this.accountsService.getById(id)
        .then(
          account => this.account = account,
          response => {
            if (response.status == 404) {
              this.router.navigate(['NotFound']);
            }
          });
    });
  }

  onSubmit(): void {
    console.log(this.account);
    if (this.account.id){
        this.accountsService.update(this.account)
          .then(account => {
            this.accounts = null;
            this.router.navigate(['account']);
        });
    } else {
        this.accountsService.post(this.account)
          .then(account => {
            this.accounts = null;
            this.router.navigate(['account']);
        });
    }
   
  }

}
