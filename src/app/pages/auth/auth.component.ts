import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {MatButtonModule} from '@angular/material/button';
import { AuthServiceService } from '../../services/Auth/auth-service.service';
import { consumerAfterComputation } from '@angular/core/primitives/signals';


@Component({
  selector: 'app-auth',
  standalone: true,
  imports: [CommonModule ,
    FormsModule, 
    MatFormFieldModule, 
    MatInputModule,
    MatButtonModule,
    ReactiveFormsModule
],
  templateUrl: './auth.component.html',
  styleUrl: './auth.component.scss'
})
export class AuthComponent {

  isRegister = false;

  constructor(public authService:AuthServiceService){}


  registerationForm = new FormGroup({
    fullName:new FormControl("",[Validators.required]),
    email:new FormControl("",[Validators.required,Validators.email]),
    password:new FormControl("",[
      Validators.required,
      Validators.minLength(6)]),

  });

  loginForm = new FormGroup({
    email:new FormControl("",[Validators.required,Validators.email]),
    password:new FormControl("",[
      Validators.required]),

  });
  handleRegister(){
    console.log("Register",this.registerationForm.value)
    this.authService.register(this.registerationForm.value).subscribe({
      next:(response)=>{
        localStorage.setItem("jwt",response.jwt);
        this.authService.getUserProfile().subscribe();
        console.log("Signup Success",response)
      }
    })
  }

  handleLogin(){
    console.log("Login",this.loginForm.value)
    this.authService.login(this.loginForm.value).subscribe({
      next:(response)=>{
        localStorage.setItem("jwt",response.jwt);
        this.authService.getUserProfile().subscribe();
        console.log("Login Success",response)
      }
    })
    
  }

  togglePanel(){
    this.isRegister =! this.isRegister;

  }

}
