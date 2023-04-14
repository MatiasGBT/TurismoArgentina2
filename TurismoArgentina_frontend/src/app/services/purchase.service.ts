import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { Purchase } from '../models/purchase';
import { CatchErrorService } from './catch-error.service';

@Injectable({
  providedIn: 'root'
})
export class PurchaseService {
  private baseUrl = "http://localhost:8090/api/purchases";

  constructor(private http: HttpClient, private catchErrorService: CatchErrorService) { }

  public create(purchase: Purchase): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/`, purchase).pipe(
      catchError(ex => {
        this.catchErrorService.showError(ex);
        return throwError(() => ex);
      })
    );
  }

  public getCount(refunded: boolean): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/admin/count/${refunded}`).pipe(
      catchError(ex => throwError(() => ex))
    );
  }

  public getMoney(refunded: boolean): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/admin/money/${refunded}`).pipe(
      catchError(ex => throwError(() => ex))
    );
  }
}
