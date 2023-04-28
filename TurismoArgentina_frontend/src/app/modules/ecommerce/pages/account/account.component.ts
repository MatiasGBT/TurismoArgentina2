import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { KeycloakService } from 'keycloak-angular';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {
  public selectedLanguage: string = this.translate.currentLang;
  public langs: string[] = this.translate.getLangs();
  public currentLang: string = this.translate.currentLang;
  public userIsLoggedIn: boolean = false;

  constructor(private keycloakService: KeycloakService, private translate: TranslateService,
    private authService: AuthService) { }

  ngOnInit(): void {
    if (this.authService.keycloakUser) this.userIsLoggedIn = true;
  }

  public goToAccountDashboard(): void {
    this.keycloakService.getKeycloakInstance().accountManagement();
  }

  public changeLanguage(): void {
    this.translate.use(this.selectedLanguage);
    localStorage.setItem('lang', this.selectedLanguage);
  }

  public logout(): void {
    sessionStorage.removeItem('user');
    this.keycloakService.getKeycloakInstance().logout();
  }
}
