import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { AuthService } from './services/auth.service';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  
  constructor(private translate: TranslateService, private authService: AuthService,
    private keycloakService: KeycloakService) {
    translate.addLangs(['en', 'es', 'pt']);
    translate.setDefaultLang('en');
  }

  ngOnInit(): void {
    this.setLang();
    this.loginIfTokenIsNotEmpty();
  }

  private setLang(): void {
    const lang = localStorage.getItem('lang');
    lang ? this.translate.use(lang) : this.translate.use('en');
  }

  private loginIfTokenIsNotEmpty(): void {
    console.log(this.keycloakService.getKeycloakInstance().token)
    if (this.keycloakService.getKeycloakInstance().token) {
      this.authService.login().subscribe(response => {
        this.authService.setKeycloakUser(response.user);
        console.log(response.message);
        this.authService.userLoggedInEvent.emit();
      });
    }
  }
}
