import { MovementsFormComponent } from './movements/movements-form/movements-form.component';
import { MovementsComponent } from './movements/movements.component';
import { EstablishmentsComponent } from './establishments/establishments.component';
import { AccountsComponent } from './accounts/accounts.component';
import { AccountsFormComponent } from './accounts/accounts-form/accounts-form.component';
import { CategorysFormComponent } from './categorys/categorys-form/categorys-form.component';
import { CategorysComponent } from './categorys/categorys.component';
import { NgModule } from '@angular/core';
import { CommonModule, } from '@angular/common';
import { BrowserModule  } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home/home.component';
import { UserComponent } from './user/user.component';
import { TablesComponent } from './tables/tables.component';
import { TypographyComponent } from './typography/typography.component';
import { IconsComponent } from './icons/icons.component';
import { MapsComponent } from './maps/maps.component';
import { NotificationsComponent } from './notifications/notifications.component';
import { UpgradeComponent } from './upgrade/upgrade.component';
import { EstablishmentsFormComponent } from './establishments/establishments-form/establishments-form.component';

const routes: Routes =[
    { path: 'dashboard',                component: HomeComponent },
    { path: 'category',                 component: CategorysComponent },
    { path: 'category-form',            component: CategorysFormComponent },
    { path: 'category-form/:id',        component: CategorysFormComponent },
    { path: 'account',                  component: AccountsComponent },
    { path: 'account-form',             component: AccountsFormComponent },
    { path: 'account-form/:id',         component: AccountsFormComponent },
    { path: 'establishment',            component: EstablishmentsComponent },
    { path: 'establishment-form',       component: EstablishmentsFormComponent },
    { path: 'establishment-form/:id',   component: EstablishmentsFormComponent },
    { path: 'movement',                 component: MovementsComponent },
    { path: 'movement-form',            component: MovementsFormComponent },
    { path: 'movement-form/:id',        component: MovementsFormComponent },
    { path: 'user',                     component: UserComponent },
    { path: 'table',                    component: TablesComponent },
    { path: 'typography',               component: TypographyComponent },
    { path: 'icons',                    component: IconsComponent },
    { path: 'maps',                     component: MapsComponent },
    { path: 'notifications',            component: NotificationsComponent },
    { path: 'upgrade',                  component: UpgradeComponent },
    { path: '',                         redirectTo: 'dashboard', pathMatch: 'full' }
];

@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    RouterModule.forRoot(routes)
  ],
  exports: [
  ],
})
export class AppRoutingModule { }
