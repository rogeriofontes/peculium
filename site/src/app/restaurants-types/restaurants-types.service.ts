import { Injectable } from '@angular/core';
import { Headers, Http, RequestOptions, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { environment } from '../../environments/environment';

import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';
import 'rxjs/Rx';

import { RestaurantType } from './restaurant-type';

@Injectable()
export class RestaurantsTypesService {
    public token: string;
    private currentUser;
    private options;
  
    private headers = new Headers({ 'Content-Type': 'application/json' }); 
    private apiUrl = environment.apiUrl + '/restaurant-types'; 

    constructor(private http: Http) { 
        this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
        this.token = this.currentUser && this.currentUser.token;
        this.headers.append('Authorization', 'Bearer ' +  this.token);
        this.options = new RequestOptions({ headers:  this.headers });
    }

    //Metodo GET all
    get(): Promise<RestaurantType[]> {
        return this.http.get(this.apiUrl, this.options)
            .toPromise()
            .then(response => response.json() as RestaurantType[])
            //.catch(this.handleError);
    }

    //busca pelo nome
    getByName(name: string): Promise<RestaurantType> {
        const url = `${this.apiUrl}/find-by-name/${name}`;
        console.log('->--'+url);
        return this.http.get(url, this.options)
            .toPromise()
            .then(response => response.json() as RestaurantType)
            //.catch(this.handleError);
    }


  //busca pelo id
    getById(id: number): Promise<RestaurantType> {
        const url = `${this.apiUrl}/${id}`;
        return this.http.get(url, this.options)
            .toPromise()
            .then(response => response.json() as RestaurantType)
        
            //.catch(this.handleError);
    }
    //Metodo POST
    post(restaurantType: RestaurantType): Promise<RestaurantType> {
        return this.http
            .post(this.apiUrl, JSON.stringify(restaurantType), this.options)
            .toPromise()
            //valida resposta
            .then(res => res.json() as RestaurantType)
            //.catch(this.handleError);
    }
    
     //Metodo POST
    update(restaurantType: RestaurantType): Promise<RestaurantType> {
        const url = `${this.apiUrl}/${restaurantType.id}`;
        return this.http
            .put(url, JSON.stringify(restaurantType), this.options)
            .toPromise()
            //valida resposta
            .then(res => res.json() as RestaurantType)
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
