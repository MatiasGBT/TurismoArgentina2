import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, Observable, throwError } from 'rxjs';
import { Province } from '../models/province';
import { CatchErrorService } from './catch-error.service';

@Injectable({
  providedIn: 'root'
})
export class ProvinceService {
  private baseUrl = "http://localhost:8090/api/provinces";

  constructor(private router: Router, private http: HttpClient,
    private catchErrorService: CatchErrorService) { }

  public navigateToProvince(idProvince: number): void {
    this.router.navigate([`shop/provinces/${idProvince}`]);
  }

  public getAll(page: number, areDeleted: boolean): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/list/${page}/${areDeleted}`).pipe(
      catchError(ex => {
        this.catchErrorService.showError(ex);
        return throwError(() => ex);
      })
    );
  }

  public getAllByName(page: number, areDeleted: boolean, name: string): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/list/${page}/${areDeleted}/${name}`).pipe(
      catchError(ex => {
        this.catchErrorService.showError(ex);
        return throwError(() => ex);
      })
    );
  }

  public getThreeRandom(): Observable<Province[]> {
    return this.http.get<Province[]>(`${this.baseUrl}/random`).pipe(
      catchError(ex => {
        this.catchErrorService.showError(ex);
        return throwError(() => ex);
      })
    );
  }

  public getAllProvinceNames(): Observable<string[]> {
    return this.http.get<string[]>(`${this.baseUrl}/names`).pipe(
      catchError(ex => {
        this.catchErrorService.showError(ex);
        return throwError(() => ex);
      })
    );
  }

  public getById(idProvince: number): Observable<Province> {
    return this.http.get<Province>(`${this.baseUrl}/${idProvince}`).pipe(
      catchError(ex => {
        this.router.navigate(['/shop/provinces']);
        this.catchErrorService.showError(ex);
        return throwError(() => ex);
      })
    );
  }

  public getByName(name: string): Observable<Province> {
    return this.http.get<Province>(`${this.baseUrl}?name=${name}`).pipe(
      catchError(ex => {
        this.router.navigate(['/shop/provinces']);
        this.catchErrorService.showError(ex);
        return throwError(() => ex);
      })
    );
  }

  public getCount(): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/admin/count`).pipe(
      catchError(ex => throwError(() => ex))
    );
  }

  public update(province: Province): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/admin`, province).pipe(
      catchError(ex => {
        this.catchErrorService.showError(ex);
        return throwError(() => ex);
      })
    );
  }

  public create(province: Province): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/admin`, province).pipe(
      catchError(ex => {
        this.catchErrorService.showError(ex);
        return throwError(() => ex);
      })
    );
  }

  public uploadPhoto(image: File, idProvince: number): Observable<any> {
    let formData = new FormData();
    formData.append("image", image);
    formData.append("id", idProvince.toString());
    return this.http.post(`${this.baseUrl}/admin/img`, formData).pipe(
      catchError(ex => {
        this.catchErrorService.showError(ex);
        return throwError(() => ex);
      })
    );
  }
}
