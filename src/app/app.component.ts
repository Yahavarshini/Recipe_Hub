import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from './pages/navbar/navbar.component';
import { FooterComponent } from './pages/footer/footer.component';
import { HomeComponent } from './pages/home/home.component';
import { AuthComponent } from './pages/auth/auth.component';
import { AuthServiceService } from './services/Auth/auth-service.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,   
    NavbarComponent,
    AuthComponent,
    HomeComponent,
    FooterComponent
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'recipe-sharing-hub';

  user:any=null;

  constructor(public authService:AuthServiceService){}

  ngOnInit(){

    this.authService.getUserProfile().subscribe({
      next:data =>console.log("req user ",data),
      error: error=>console.log("error",error)
    });
    this.authService.authSubject.subscribe(
      (auth)=>{
        console.log("auth state ",auth)
        this.user=auth.user
      }
    )

  }

}
