import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseUrl = "http://localhost:8090/api/users";

  constructor(private http: HttpClient) { }

  public getCount(): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/admin/count`).pipe(
      catchError(ex => throwError(() => ex))
    );
  }
}
