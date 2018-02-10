import { Injectable } from '@angular/core';
import { Headers, Http, RequestOptions, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { environment } from '../../environments/environment';

import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';
import 'rxjs/Rx';

import { Restaurante } from './restaurante';

@Injectable()
export class RestauranteService {
    public token: string;
    private currentUser;
    private options;

    private headers = new Headers({ 'Content-Type': 'application/json' });  
    private apiUrl = environment.apiUrl + '/restaurants'; 

    constructor(private http: Http) { 
        console.log(' local: '+ localStorage.getItem('currentUser'));
        this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
        this.token = this.currentUser && this.currentUser.token;
        this.headers.append('Authorization', 'Bearer ' +  this.token);
        this.options = new RequestOptions({ headers:  this.headers });
    }

    //Metodo GET all
    get(): Promise<Restaurante[]> {
        return this.http.get(this.apiUrl, this.options)
            .toPromise()
            .then(response => response.json() as Restaurante[])
            //.catch(this.handleError);
    }

    //busca pelo nome
    getByName(name: string): Promise<Restaurante> {
        const url = `${this.apiUrl}/find-by-name/${name}`;
        return this.http.get(url)
            .toPromise()
            .then(response => response.json() as Restaurante)
        
            //.catch(this.handleError);
    }

    //busca pelo id
    getById(id: number): Promise<Restaurante> {
        const url = `${this.apiUrl}/${id}`;
        return this.http.get(url, this.options)
            .toPromise()
            .then(response => response.json() as Restaurante)
        
            //.catch(this.handleError);
    }

    //busca pelo id
    getPlatesById(id: number): Promise<Restaurante> {
        const url = `${this.apiUrl}/${id}/plates`;
        return this.http.get(url, this.options)
            .toPromise()
            .then(response => response.json() as Restaurante)
        
            //.catch(this.handleError);
    }

    //Metodo POST
    post(restaurante: Restaurante): Promise<Restaurante> {
        return this.http
            .post(this.apiUrl, JSON.stringify(restaurante), this.options)
            .toPromise()
            //valida resposta
            .then(res => res.json() as Restaurante)
            //.catch(this.handleError);
    }
    
     //Metodo POST
    update(restaurante: Restaurante): Promise<Restaurante> {
        const url = `${this.apiUrl}/${restaurante.id}`;
        return this.http
            .put(url, JSON.stringify(restaurante), this.options)
            .toPromise()
            //valida resposta
            .then(res => res.json() as Restaurante)
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