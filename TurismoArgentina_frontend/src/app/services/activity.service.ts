import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { catchError, Observable, of, throwError } from 'rxjs';
import Swal from 'sweetalert2';
import { Activity } from '../models/activity';
import { CatchErrorService } from './catch-error.service';

@Injectable({
  providedIn: 'root'
})
export class ActivityService {
  private baseUrl = "http://localhost:8090/api/activities";
  private cartActivities: Activity[] = [];

  constructor(private router: Router, private http: HttpClient,
    private catchErrorService: CatchErrorService, private translate: TranslateService) { }

  public navigateToActivity(idActivity: number): void {
    this.router.navigate([`shop/activities/${idActivity}`]);
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

  public getFiveRandom(): Observable<Activity[]> {
    return this.http.get<Activity[]>(`${this.baseUrl}/random`).pipe(
      catchError(ex => {
        this.catchErrorService.showError(ex);
        return throwError(() => ex);
      })
    );
  }

  public getById(idActivity: number): Observable<Activity> {
    return this.http.get<Activity>(`${this.baseUrl}/${idActivity}`).pipe(
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

  public addToCart(actity: Activity): boolean {
    //It is necessary to do it this way so that the position in memory of the objects
    //is not validated and the same activity cannot be added twice by re-entering the
    //page of that activity.
    let array = JSON.stringify(this.cartActivities);
    if (!array.includes(JSON.stringify(actity))) {
      this.cartActivities.push(actity);
      return true;
    } else {
      this.translate.get('ECOMMERCE.CART.ACTIVITIES.ALREADY_IN_CART').subscribe(response => {
        this.fireAlreadyInCartModal(response);
      });
      return false;
    }
    //The function returns "true" if the selected activity is not in the cart and
    //returns "false" if the activity is in the cart.
  }

  private fireAlreadyInCartModal(title: string): void {
    Swal.fire({icon: 'error', title: title, showConfirmButton: false, timer: 2000, timerProgressBar: true});
  }

  public deleteFromCart(idActivity: number): void {
    this.cartActivities = this.cartActivities.filter(activity => activity.idActivity != idActivity);
  }

  public getCartActivities(): Activity[] {
    return this.cartActivities;
  }

  public emptyCartActivities(): void {
    this.cartActivities = [];
  }

  public getCount(): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/admin/count`).pipe(
      catchError(ex => throwError(() => ex))
    );
  }

  public update(activity: Activity): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/admin`, activity).pipe(
      catchError(ex => {
        this.catchErrorService.showError(ex);
        return throwError(() => ex);
      })
    );
  }

  public create(activity: Activity): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/admin`, activity).pipe(
      catchError(ex => {
        this.catchErrorService.showError(ex);
        return throwError(() => ex);
      })
    );
  }

  public async uploadPhoto(image: File, idActivity: number, imageNumber: number): Promise<any> {
    let formData = new FormData();
    formData.append("image", image);
    formData.append("id", idActivity.toString());
    formData.append("imageNumber", imageNumber.toString());
    return new Promise<string>((resolve) => {
      this.http.post(`${this.baseUrl}/admin/img`, formData).pipe(
        catchError(ex => {
          this.catchErrorService.showError(ex);
          return throwError(() => ex);
        })
      ).subscribe(response => resolve(JSON.stringify(response)));
    });
  }
}
