import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AppAdminRoutingModule } from './app-admin-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { provideHttpClient } from '@angular/common/http';
import { SignupComponent } from './signup/signup.component';
import { ForgottenPasswordComponent } from './forgotten-password/forgotten-password.component';
import { HomeComponent } from './home/home.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';


@NgModule({
  declarations: [
    SignupComponent, 
    ForgottenPasswordComponent, HomeComponent, ChangePasswordComponent, LoginComponent, LogoutComponent],
  imports: [
    CommonModule,
    AppAdminRoutingModule,
    FormsModule,
    ReactiveFormsModule

  ],
  providers:[provideHttpClient()]
})
export class AppAdminModule { }
