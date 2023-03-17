import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, Observable, throwError } from 'rxjs';
import { Activity } from '../models/activity';
import { CatchErrorService } from './catch-error.service';

@Injectable({
  providedIn: 'root'
})
export class ActivityService {
  private baseUrl = "http://localhost:8090/api/activities";

  constructor(private router: Router, private http: HttpClient,
    private catchErrorService: CatchErrorService) { }

  public navigateToActivity(idActivity: number): void {
    this.router.navigate([`shop/activities/${idActivity}`]);
  }

  public getAll(page: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/${page}`).pipe(
      catchError(ex => {
        this.catchErrorService.showError(ex);
        return throwError(() => ex);
      })
    );
  }

  public getFiveRandom(): Observable<Activity[]> {
    return this.http.get<Activity[]>(`${this.baseUrl}/random`).pipe(
      catchError(ex => {
        this.catchErrorService.showError(ex);
        return throwError(() => ex);
      })
    );
  }

  public getById(idActivity: number): Observable<Activity> {
    return this.http.get<Activity>(`${this.baseUrl}/id/${idActivity}`).pipe(
      catchError(ex => {
        this.catchErrorService.showError(ex);
        return throwError(() => ex);
      })
    );
  }

  public getByLocationName(page: number, locationName: string): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/${page}/${locationName}`).pipe(
      catchError(ex => {
        this.catchErrorService.showError(ex);
        return throwError(() => ex);
      })
    );
  }
}
