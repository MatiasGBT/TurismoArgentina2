import { Injectable } from '@angular/core';
import { CatchErrorService } from './catch-error.service';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { AuthService } from './auth.service';
import { RedeemedCoupon } from '../models/redeemed-coupon';

@Injectable({
  providedIn: 'root'
})
export class CouponService {
  private baseUrl = "http://localhost:8090/api/coupons";

  constructor(private http: HttpClient, private catchErrorService: CatchErrorService,
    private authService: AuthService) { }

  public getAll(page: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/admin/list/${page}`).pipe(
      catchError(ex => {
        this.catchErrorService.showError(ex);
        return throwError(() => ex);
      })
    );
  }

  public getAllByName(page: number, name: string): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/admin/list/${page}/${name}`).pipe(
      catchError(ex => {
        this.catchErrorService.showError(ex);
        return throwError(() => ex);
      })
    );
  }

  public redeemCoupon(couponName: string): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/${couponName}/${this.authService.keycloakUser.idUser}`, null).pipe(
      catchError(ex => {
        this.catchErrorService.showError(ex);
        return throwError(() => ex);
      })
    );
  }

  public updateRedeemedCoupon(redeemedCoupon: RedeemedCoupon): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/redeemed`, redeemedCoupon).pipe(
      catchError(ex => {
        this.catchErrorService.showError(ex);
        return throwError(() => ex);
      })
    );
  }

  public delete(idCoupon: number): Observable<any> {
    return this.http.delete<any>(`${this.baseUrl}/admin/${idCoupon}`).pipe(
      catchError(ex => {
        this.catchErrorService.showError(ex);
        return throwError(() => ex);
      })
    );
  }
}
