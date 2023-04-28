import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  
  constructor(private translate: TranslateService, private authService: AuthService) {
    translate.addLangs(['en', 'es', 'pt']);
    translate.setDefaultLang('en');
  }

  ngOnInit(): void {
    const lang = localStorage.getItem('lang');
    lang ? this.translate.use(lang) : this.translate.use('en');
    if (sessionStorage.getItem('user') && this.authService.tokenIsEmpty()) {
      this.authService.login().subscribe(response => {
        this.authService.setKeycloakUser(response.user);
        sessionStorage.setItem('user', JSON.stringify(response.user));
        console.log(response.message);
        this.authService.userLoggedInEvent.emit();
      });
    }
  }
}
