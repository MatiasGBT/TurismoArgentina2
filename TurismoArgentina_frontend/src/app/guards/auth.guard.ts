import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Router, RouterStateSnapshot } from '@angular/router';
import { KeycloakAuthGuard, KeycloakService } from 'keycloak-angular';
import Swal from 'sweetalert2';
import { AuthService } from '../services/auth.service';
import { TranslateTextService } from '../services/translate-text.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard extends KeycloakAuthGuard {
  constructor(protected override readonly router: Router, protected readonly keycloak: KeycloakService,
    private authService: AuthService, private translateTextService: TranslateTextService) {
    super(router, keycloak);
  }

  public async isAccessAllowed(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    await this.checkIfIsAuthenticated(state);
    let role = route.data['role'] as string;
    return this.checkIfHasRole(role);
  }

  private async checkIfIsAuthenticated(state: RouterStateSnapshot): Promise<any> {
    if (!this.authenticated) {
      await this.keycloak.login({
        redirectUri: window.location.origin + state.url
      });
    }
    if (this.authService.tokenIsEmpty()) {
      this.authService.login().subscribe(response => {
        this.authService.setKeycloakUser(response.user);
        console.log(response.message);
        this.authService.userLoggedInEvent.emit();
      });
    }
  }

  private checkIfHasRole(role: string): boolean {
    if (this.authService.hasRole(role)) return true;
    else {
      this.router.navigate(['/shop']);
      this.fireModal();
      return false;
    }
  }

  private fireModal(): void {
    Swal.fire({
      title: this.translateTextService.getTranslatedStringByUrl('GENERAL.UNAUTHORISED_TITLE'),
      text: this.translateTextService.getTranslatedStringByUrl('GENERAL.UNAUTHORISED_TEXT'),
      icon: 'error', showConfirmButton: false, timer: 3000, timerProgressBar: true
    });
  }
}