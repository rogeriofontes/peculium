import { Injectable } from '@angular/core';
import { Headers, Http, RequestOptions, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { environment } from '../../environments/environment';

import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';
import 'rxjs/Rx';

import { Location } from './location';

@Injectable()
export class LocationService {
  public token: string;
  private currentUser;
  private options;

  private headers = new Headers({ 'Content-Type': 'application/json' }); 
  private apiUrl = environment.apiUrl + '/locations'; 

  constructor(private http: Http) { 
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    //this.token = this.currentUser && this.currentUser.token;
    this.token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJmb250ZXN0ekBnbWFpbC5jb20iLCJleHAiOjE1MTk4MzQ3NzV9.yXhUOqLPWCcxDFfTd1Yf1hq-96jzfVmXR7AbIXFMjiERHxdHx8gwpsyPOsDG-X9RvNm-YZNVJLehkDat35nkbw";
    this.headers.append('Authorization', 'Bearer ' +  this.token);
    this.options = new RequestOptions({ headers:  this.headers });
}

//Metodo GET all
public get(): Promise<Location[]> {
  return this.http.get(this.apiUrl, this.options)
      .toPromise()
      .then(response => response.json() as Location[])
      //.catch(this.handleError);
}

//busca pelo nome
public getByName(name: string): Promise<Location> {
  const url = `${this.apiUrl}/find-by-name/${name}`;
  console.log('->--'+url);
  return this.http.get(url, this.options)
      .toPromise()
      .then(response => response.json() as Location)
      //.catch(this.handleError);
}


//busca pelo id
public getById(id: number): Promise<Location> {
  const url = `${this.apiUrl}/${id}`;
  return this.http.get(url, this.options)
      .toPromise()
      .then(response => response.json() as Location)
  
      //.catch(this.handleError);
}

//Metodo POST
public post(location: Location): Promise<Location> {
  return this.http
      .post(this.apiUrl, JSON.stringify(location), this.options)
      .toPromise()
      //valida resposta
      .then(res => res.json() as Location)
      //.catch(this.handleError);
}

//Metodo POST
public update(location: Location): Promise<Location> {
  const url = `${this.apiUrl}/${location.id}`;
  return this.http
      .put(url, JSON.stringify(location), this.options)
      .toPromise()
      //valida resposta
      .then(res => res.json() as Location)
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
