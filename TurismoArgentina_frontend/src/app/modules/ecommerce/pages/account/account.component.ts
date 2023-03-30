import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {
  public selectedLanguage: string = this.translate.currentLang;
  public langs: string[] = this.translate.getLangs();
  public currentLang: string = this.translate.currentLang;

  constructor(private keycloakService: KeycloakService, private translate: TranslateService) { }

  ngOnInit(): void {
  }

  public goToAccountDashboard(): void {
    this.keycloakService.getKeycloakInstance().accountManagement();
  }

  public changeLanguage(): void {
    this.translate.use(this.selectedLanguage);
    localStorage.setItem('lang', this.selectedLanguage);
  }

  public logout(): void {
    this.keycloakService.getKeycloakInstance().logout();
  }
}
