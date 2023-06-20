import { Injectable } from '@angular/core';
import { CatchErrorService } from './catch-error.service';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { AuthService } from './auth.service';
import { RedeemedCoupon } from '../models/redeemed-coupon';
import { Coupon } from '../models/coupon';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class CouponService {
  private baseUrl = "http://localhost:8090/api/coupons";

  constructor(private http: HttpClient, private catchErrorService: CatchErrorService,
    private authService: AuthService, private router: Router) { }

  public getAll(page: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/admin?page=${page}`).pipe(
      catchError(ex => {
        this.catchErrorService.showError(ex);
        return throwError(() => ex);
      })
    );
  }

  public getAllByName(page: number, name: string): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/admin?page=${page}&name=${name}`).pipe(
      catchError(ex => {
        this.catchErrorService.showError(ex);
        return throwError(() => ex);
      })
    );
  }

  public getById(idCoupon: number): Observable<Coupon> {
    return this.http.get<Coupon>(`${this.baseUrl}/admin/${idCoupon}`).pipe(
      catchError(ex => {
        this.router.navigate(['/admin/coupons']);
        this.catchErrorService.showError(ex);
        return throwError(() => ex);
      })
    );
  }

  public redeemCoupon(couponName: string): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}?couponName=${couponName}&idUser=${this.authService.keycloakUser.idUser}`, null);
  }

  public updateRedeemedCoupon(redeemedCoupon: RedeemedCoupon): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/redeemed`, redeemedCoupon).pipe(
      catchError(ex => {
        this.catchErrorService.showError(ex);
        return throwError(() => ex);
      })
    );
  }

  public update(coupon: Coupon): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/admin`, coupon).pipe(
      catchError(ex => {
        this.catchErrorService.showError(ex);
        return throwError(() => ex);
      })
    );
  }

  public create(coupon: Coupon): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/admin`, coupon).pipe(
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
