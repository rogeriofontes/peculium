import { Movement } from './movement';
import { Injectable } from '@angular/core';
import { Headers, Http, RequestOptions, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { environment } from '../../environments/environment';

import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';
import 'rxjs/Rx';

@Injectable()
export class MovementsService {
    public token: string;
    private currentUser;
    private options;

    private headers = new Headers({ 'Content-Type': 'application/json' });  
    private apiUrl = environment.apiUrl + '/movements'; 

    constructor(private http: Http) { 
        console.log(' local: '+ localStorage.getItem('currentUser'));
        this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
        this.token = this.currentUser && this.currentUser.token;
        this.headers.append('Authorization', 'Bearer ' +  this.token);
        this.options = new RequestOptions({ headers:  this.headers });
    }

    //Metodo GET all
    get(): Promise<Movement[]> {
        return this.http.get(this.apiUrl, this.options)
            .toPromise()
            .then(response => response.json() as Movement[])
            //.catch(this.handleError);
    }

    //busca pelo nome
    getByName(name: string): Promise<Movement> {
        const url = `${this.apiUrl}/find-by-name/${name}`;
        return this.http.get(url)
            .toPromise()
            .then(response => response.json() as Movement)
        
            //.catch(this.handleError);
    }

    //busca pelo id
    getById(id: number): Promise<Movement> {
        const url = `${this.apiUrl}/${id}`;
        return this.http.get(url, this.options)
            .toPromise()
            .then(response => response.json() as Movement)
        
            //.catch(this.handleError);
    }

    //busca pelo id
    getPlatesById(id: number): Promise<Movement> {
        const url = `${this.apiUrl}/${id}/plates`;
        return this.http.get(url, this.options)
            .toPromise()
            .then(response => response.json() as Movement)
        
            //.catch(this.handleError);
    }

    //Metodo POST
    post(movement: Movement): Promise<Movement> {
        return this.http
            .post(this.apiUrl, JSON.stringify(movement), this.options)
            .toPromise()
            //valida resposta
            .then(res => res.json() as Movement)
            //.catch(this.handleError);
    }
    
     //Metodo POST
    update(movement: Movement): Promise<Movement> {
        const url = `${this.apiUrl}/${movement.id}`;
        return this.http
            .put(url, JSON.stringify(movement), this.options)
            .toPromise()
            //valida resposta
            .then(res => res.json() as Movement)
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