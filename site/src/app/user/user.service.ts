import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map'
 
import { AuthenticationService } from './authentication.service';
import { User } from './user';

@Injectable()
export class UserService {
  public token: string;
  private currentUser;
  private options;

  private headers = new Headers({ 'Content-Type': 'application/json' }); 

  constructor(  
        private http: Http,
        private authenticationService: AuthenticationService
  ) { 
        this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
        this.token = this.currentUser && this.currentUser.token;
        this.headers.append('Authorization', 'Bearer ' +  this.token);
        this.options = new RequestOptions({ headers: this.headers });
  }

  getUsers(): Observable<User[]> {
        // get users from api
        return this.http.get('/api/users', this.options)
            .map((response: Response) => response.json());
    }
}
