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
  private cartActivities: Activity[] = [];

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
        this.router.navigate(['/shop/activities']);
        this.catchErrorService.showError(ex);
        return throwError(() => ex);
      })
    );
  }

  public getByLocationName(page: number, locationName: string): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/location/${locationName}/${page}`).pipe(
      catchError(ex => {
        this.catchErrorService.showError(ex);
        return throwError(() => ex);
      })
    );
  }

  public getByLocationId(idLocation: number): Observable<Activity[]> {
    return this.http.get<Activity[]>(`${this.baseUrl}/location/${idLocation}`).pipe(
      catchError(ex => {
        this.catchErrorService.showError(ex);
        return throwError(() => ex);
      })
    );
  }

  public addToCart(actity: Activity): void {
    //It is necessary to do it this way so that the position in memory of the objects
    //is not validated and the same activity cannot be added twice by re-entering the
    //page of that activity.
    let array = JSON.stringify(this.cartActivities);
    if (!array.includes(JSON.stringify(actity))) this.cartActivities.push(actity);
    else console.log("The activity is already in the cart");
  }

  public deleteFromCart(idActivity: number): void {
    this.cartActivities = this.cartActivities.filter(activity => activity.idActivity != idActivity);
  }

  public getCartActivities(): Activity[] {
    return this.cartActivities;
  }
}
