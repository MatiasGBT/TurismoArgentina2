import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PurchaseService {
  private baseUrl = "http://localhost:8090/api/purchases";

  constructor(private http: HttpClient) { }

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
