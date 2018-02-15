import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home/home.component';
//import { PratosComponent } from './pratos/pratos.component';
//import { PlateFormComponent } from './pratos/plate-form/plate-form.component';
//import { RestaurantesComponent } from './restaurantes/restaurantes.component';
///import { RestaurantsTypesComponent } from './restaurants-types/restaurants-types.component';
//import { RestaurantFormComponent } from './restaurantes/restaurant-form/restaurant-form.component';
//import { RestaurantTypeFormComponent } from './restaurants-types/restaurant-type-form/restaurant-type-form.component';
//import { RestaurantDetailsComponent } from './restaurantes/restaurant-details/restaurant-details.component';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './user/auth-guard';

const APP_ROUTES: Routes = [
    { path: '', component: HomeComponent, canActivate: [AuthGuard] },
    { path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
  //  { path: 'restaurants-types', component: RestaurantsTypesComponent, canActivate: [AuthGuard] },
  //  { path: 'restaurants-types/form', component: RestaurantTypeFormComponent, canActivate: [AuthGuard] },
  //  { path: 'restaurants-types/form/:id', component: RestaurantTypeFormComponent, canActivate: [AuthGuard] },
 //   { path: 'restaurantes', component: RestaurantesComponent, canActivate: [AuthGuard] },
  //  { path: 'restaurantes/form', component: RestaurantFormComponent, canActivate: [AuthGuard] },
   // { path: 'restaurantes/form/:id', component: RestaurantFormComponent, canActivate: [AuthGuard] },
  //  { path: 'pratos', component: PratosComponent, canActivate: [AuthGuard]},
  //  { path: 'pratos/form', component: PlateFormComponent, canActivate: [AuthGuard] },
  //  { path: 'pratos/form/:id', component: PlateFormComponent, canActivate: [AuthGuard] },
  //  { path: 'restaurantes/:id', component: RestaurantDetailsComponent, canActivate: [AuthGuard] },
    { path: 'login', component: LoginComponent }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(APP_ROUTES);