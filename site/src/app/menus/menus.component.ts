import { Component, OnInit } from '@angular/core';
import { AuthGuard } from '../user/auth-guard';

@Component({
  selector: 'app-menus',
  templateUrl: './menus.component.html',
  styleUrls: ['./menus.component.css']
})
export class MenusComponent implements OnInit {
  isSignedIn: boolean;

  constructor(private authGuard: AuthGuard) { 
    this.isSignedIn = this.authGuard.canActivate();
    console.log('Session: ' + this.isSignedIn);
  }

  ngOnInit() {
  }

}
