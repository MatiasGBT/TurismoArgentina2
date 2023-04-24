import { Injectable } from '@angular/core';
import { CatchErrorService } from './catch-error.service';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class CouponService {
  private baseUrl = "http://localhost:8090/api/coupons";

  constructor(private http: HttpClient, private catchErrorService: CatchErrorService,
    private authService: AuthService) {}

  public redeemCoupon(couponName: string): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/${couponName}/${this.authService.keycloakUser.idUser}`, null).pipe(
      catchError(ex => {
        this.catchErrorService.showError(ex);
        return throwError(() => ex);
      })
    );
  }

  public updateRedeemedCoupon(redeemedCoupon: any): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/redeemed`, redeemedCoupon).pipe(
      catchError(ex => {
        this.catchErrorService.showError(ex);
        return throwError(() => ex);
      })
    );
  }
}
