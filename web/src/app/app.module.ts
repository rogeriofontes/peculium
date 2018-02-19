import { EstablishmentsService } from './establishments/establishments.service';
import { CategorysService } from './categorys/categorys.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

import { AppRoutingModule } from './app.routing';
import { NavbarModule } from './shared/navbar/navbar.module';
import { FooterModule } from './shared/footer/footer.module';
import { SidebarModule } from './sidebar/sidebar.module';
import { LbdModule } from './lbd/lbd.module';

import { AppComponent } from './app.component';

import { HomeComponent } from './home/home.component';
import { UserComponent } from './user/user.component';
import { TablesComponent } from './tables/tables.component';
import { TypographyComponent } from './typography/typography.component';
import { IconsComponent } from './icons/icons.component';
import { MapsComponent } from './maps/maps.component';
import { NotificationsComponent } from './notifications/notifications.component';
import { UpgradeComponent } from './upgrade/upgrade.component';
import { CategorysComponent } from './categorys/categorys.component';
import { CategorysFormComponent } from './categorys/categorys-form/categorys-form.component';
import { AccountsComponent } from './accounts/accounts.component';
import { AccountsFormComponent } from './accounts/accounts-form/accounts-form.component';
import { AccountsService } from './accounts/accounts.service';
import { EstablishmentsComponent } from './establishments/establishments.component';
import { EstablishmentsFormComponent } from './establishments/establishments-form/establishments-form.component';
import { LocationService } from './addresses/location.service';
import { MovementsComponent } from './movements/movements.component';
import { MovementsFormComponent } from './movements/movements-form/movements-form.component';
import { MovementsService } from './movements/movements.service';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    UserComponent,
    TablesComponent,
    TypographyComponent,
    IconsComponent,
    MapsComponent,
    NotificationsComponent,
    UpgradeComponent,
    CategorysComponent,
    CategorysFormComponent,
    AccountsComponent,
    AccountsFormComponent,
    EstablishmentsComponent,
    EstablishmentsFormComponent,
    MovementsComponent,
    MovementsFormComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    NavbarModule,
    FooterModule,
    SidebarModule,
    RouterModule,
    AppRoutingModule,
    LbdModule,
    ReactiveFormsModule,
    CommonModule
  ],
  providers: [CategorysService, AccountsService, LocationService, EstablishmentsService, MovementsService],  
  bootstrap: [AppComponent]
})
export class AppModule { }
