import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, Observable, throwError } from 'rxjs';
import { Location } from '../models/location';
import { CatchErrorService } from './catch-error.service';

@Injectable({
  providedIn: 'root'
})
export class LocationService {
  private baseUrl = "http://localhost:8090/api/locations";

  constructor(private router: Router, private http: HttpClient,
    private catchErrorService: CatchErrorService) { }

  public navigateToLocation(idLocation: number): void {
    this.router.navigate([`shop/locations/${idLocation}`]);
  }

  public getAll(page: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/${page}`).pipe(
      catchError(ex => {
        this.catchErrorService.showError(ex);
        return throwError(() => ex);
      })
    );
  }

  public getFourRandom(): Observable<Location[]> {
    return this.http.get<Location[]>(`${this.baseUrl}/random`).pipe(
      catchError(ex => {
        this.catchErrorService.showError(ex);
        return throwError(() => ex);
      })
    );
  }

  public getAllLocationNames(): Observable<string[]> {
    return this.http.get<string[]>(`${this.baseUrl}/names`).pipe(
      catchError(ex => {
        this.catchErrorService.showError(ex);
        return throwError(() => ex);
      })
    );
  }

  public getById(idLocation: number): Observable<Location> {
    return this.http.get<Location>(`${this.baseUrl}/id/${idLocation}`).pipe(
      catchError(ex => {
        this.router.navigate(['/shop/locations']);
        this.catchErrorService.showError(ex);
        return throwError(() => ex);
      })
    );
  }

  public getByProvinceName(page: number, provinceName: string): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/province/${provinceName}/${page}`).pipe(
      catchError(ex => {
        this.catchErrorService.showError(ex);
        return throwError(() => ex);
      })
    );
  }

  public getByProvinceId(idProvince: number): Observable<Location[]> {
    return this.http.get<Location[]>(`${this.baseUrl}/province/${idProvince}`).pipe(
      catchError(ex => {
        this.catchErrorService.showError(ex);
        return throwError(() => ex);
      })
    );
  }
}
