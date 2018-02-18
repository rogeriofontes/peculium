import { Injectable } from '@angular/core';
import { Headers, Http, RequestOptions, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { environment } from '../../environments/environment';

import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';
import 'rxjs/Rx';

import { Account } from './account';

@Injectable()
export class AccountsService {
  public token: string;
  private currentUser;
  private options;

  private headers = new Headers({ 'Content-Type': 'application/json' }); 
  private apiUrl = environment.apiUrl + '/accounts'; 

  constructor(private http: Http) {
      this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
      //this.token = this.currentUser && this.currentUser.token;
      this.token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJmb250ZXN0ekBnbWFpbC5jb20iLCJleHAiOjE1MTk4MzQ3NzV9.yXhUOqLPWCcxDFfTd1Yf1hq-96jzfVmXR7AbIXFMjiERHxdHx8gwpsyPOsDG-X9RvNm-YZNVJLehkDat35nkbw";
      this.headers.append('Authorization', 'Bearer ' +  this.token);
      this.options = new RequestOptions({ headers: this.headers });
   }

       //Metodo GET all
    public get(): Promise<Account[]> {
        return this.http.get(this.apiUrl, this.options)
            .toPromise()
            .then(response => response.json() as Account[])
            //.catch(this.handleError);
    }

    //busca pelo nome
    public getByName(name: string): Promise<Account> {
        const url = `${this.apiUrl}/find-by-name/${name}`;
        return this.http.get(url, this.options)
            .toPromise()
            .then(response => response.json() as Account)
        
            //.catch(this.handleError);
    }

    //busca pelo id
    public getById(id: number): Promise<Account> {
        const url = `${this.apiUrl}/${id}`;
        return this.http.get(url, this.options)
            .toPromise()
            .then(response => response.json() as Account)
        
            //.catch(this.handleError);
    }

    //Metodo POST
    public post(account: Account): Promise<Account> {
        return this.http
            .post(this.apiUrl, JSON.stringify(account), this.options)
            .toPromise()
            //valida resposta
            .then(res => res.json() as Account)
            //.catch(this.handleError);
    }
      
     //Metodo PUT
     public update(account: Account): Promise<Account> {
        const url = `${this.apiUrl}/${account.id}`;
        return this.http
            .put(url, JSON.stringify(account), this.options)
            .toPromise()
            //valida resposta
            .then(res => res.json() as Account)
            //.catch(this.handleError);
    }

    public delete(id){
        return this.http.delete(this.getUserUrl(id), this.options)
            .toPromise()
            //valida resposta
            .then(res => console.log(res))
    }

    private getUserUrl(id){
        return this.apiUrl + "/" + id;
    }

    //trata o erro    
    // private handleError(error: any): Promise<any> {
    //     console.log(error);
    //     //console.error('An error occurred', error);
    //     return Promise.reject(error.message || error);
    // }

}
