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

  public getThreeRandom(): Observable<Province[]> {
    return this.http.get<Province[]>(`${this.baseUrl}/random`).pipe(
      catchError(ex => {
        this.catchErrorService.showError(ex);
        return throwError(() => ex);
      })
    );
  }
}
