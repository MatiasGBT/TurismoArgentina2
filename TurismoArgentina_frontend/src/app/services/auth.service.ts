import { HttpClient, HttpHeaders } from '@angular/common/http';
import { EventEmitter, Injectable, Output } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';
import { catchError, Observable, throwError } from 'rxjs';
import { User } from '../models/user';
import { CatchErrorService } from './catch-error.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private token = '';
  public keycloakUser!: User;
  private baseUrl = "http://localhost:8090/api/auth/";
  @Output() userLoggedInEvent: EventEmitter<any> = new EventEmitter();

  constructor(private keycloakService: KeycloakService, private http: HttpClient,
    private catchErrorService: CatchErrorService) { }

  public login(): Observable<any> {
    this.token = this.keycloakService.getKeycloakInstance().token || '';
    let payload = this.obtainPayload(this.token);
    let user = this.createUserWithPayload(payload);
    return this.http.post<any>(this.baseUrl + "login", user).pipe(
      catchError(ex => {
        this.catchErrorService.showError(ex);
        return throwError(() => ex);
      })
    );
  }

  public hasRole(role: string): boolean {
    let payload = this.obtainPayload(this.token);
    return payload.resource_access.springbootbackend.roles.includes(role);
  }

  public tokenIsEmpty(): boolean {
    return this.token == '';
  }

  public setKeycloakUser(user: User): void {
    this.keycloakUser = user;
  }

  private obtainPayload(access_token:string): any {
    return JSON.parse(window.atob(access_token.split(".")[1]));
  }

  private createUserWithPayload(payload: any): User {
    return {
      username: payload.preferred_username,
      name: payload.given_name,
      lastName: payload.family_name
    } as User;
  }
}
