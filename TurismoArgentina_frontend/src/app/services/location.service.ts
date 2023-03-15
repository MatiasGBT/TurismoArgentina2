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

  public getFourRandom(): Observable<Location[]> {
    return this.http.get<Location[]>(`${this.baseUrl}/random`).pipe(
      catchError(ex => {
        this.catchErrorService.showError(ex);
        return throwError(() => ex);
      })
    );
  }
}
