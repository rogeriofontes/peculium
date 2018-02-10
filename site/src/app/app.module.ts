import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { CommonModule } from '@angular/common';

import { MaterializeModule } from 'angular2-materialize';

import { AppComponent } from './app.component';
import { MenusComponent } from './menus/menus.component';
import { RestaurantesComponent } from './restaurantes/restaurantes.component';
import { PratosComponent } from './pratos/pratos.component';
import { HomeComponent } from './home/home.component';
import { routing } from './app.routing';

import { RestauranteService } from './restaurantes/restaurantes.service';
import { PratoService } from './pratos/prato.service';
import { RestaurantsTypesService } from './restaurants-types/restaurants-types.service';
import { UserService } from './user/user.service';
import { AuthenticationService } from './user/authentication.service';

import { RestaurantFormComponent } from './restaurantes/restaurant-form/restaurant-form.component';
import { PlateFormComponent } from './pratos/plate-form/plate-form.component';
import { RestaurantsTypesComponent } from './restaurants-types/restaurants-types.component';
import { RestaurantTypeFormComponent } from './restaurants-types/restaurant-type-form/restaurant-type-form.component';
import { RestaurantDetailsComponent } from './restaurantes/restaurant-details/restaurant-details.component';
import { UserComponent } from './user/user.component';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './user/auth-guard';

@NgModule({
  declarations: [
    AppComponent,
    MenusComponent,
    RestaurantesComponent,
    PratosComponent,
    HomeComponent,
    RestaurantFormComponent,
    PlateFormComponent,
    RestaurantsTypesComponent,
    RestaurantTypeFormComponent,
    RestaurantDetailsComponent,
    UserComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    MaterializeModule,
    routing,
    CommonModule
  ],
  providers: [AuthGuard, RestauranteService, PratoService, RestaurantsTypesService, UserService, AuthenticationService],
  bootstrap: [AppComponent]
})
export class AppModule { }
