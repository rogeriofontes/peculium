import { Injectable } from '@angular/core';
import { Headers, Http, RequestOptions, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { environment } from '../../environments/environment';

import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';
import 'rxjs/Rx';

import { Prato } from './prato';

@Injectable()
export class PratoService {
    public token: string;
    private currentUser;
    private options;

    private headers = new Headers({ 'Content-Type': 'application/json' }); 
    private apiUrl = environment.apiUrl + '/plates'; 
  
    constructor(private http: Http) { 
        this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
        this.token = this.currentUser && this.currentUser.token;
        this.headers.append('Authorization', 'Bearer ' +  this.token);
        this.options = new RequestOptions({ headers: this.headers });
    }

      //Metodo GET all
    get(): Promise<Prato[]> {
        return this.http.get(this.apiUrl, this.options)
            .toPromise()
            .then(response => response.json() as Prato[])
            //.catch(this.handleError);
    }

    //busca pelo nome
    getByName(name: string): Promise<Prato> {
        const url = `${this.apiUrl}/find-by-name/${name}`;
        return this.http.get(url, this.options)
            .toPromise()
            .then(response => response.json() as Prato)
        
            //.catch(this.handleError);
    }

    //busca pelo id
    getById(id: number): Promise<Prato> {
        const url = `${this.apiUrl}/${id}`;
        return this.http.get(url, this.options)
            .toPromise()
            .then(response => response.json() as Prato)
        
            //.catch(this.handleError);
    }

    //Metodo POST
    post(prato: Prato): Promise<Prato> {
        return this.http
            .post(this.apiUrl, JSON.stringify(prato), this.options)
            .toPromise()
            //valida resposta
            .then(res => res.json() as Prato)
            //.catch(this.handleError);
    }
      
     //Metodo PUT
    update(prato: Prato): Promise<Prato> {
        const url = `${this.apiUrl}/${prato.id}`;
        return this.http
            .put(url, JSON.stringify(prato), this.options)
            .toPromise()
            //valida resposta
            .then(res => res.json() as Prato)
            //.catch(this.handleError);
    }

    delete(id){
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
