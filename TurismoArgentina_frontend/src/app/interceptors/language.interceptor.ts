import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class LanguageInterceptor implements HttpInterceptor {

  constructor() {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    let lang = localStorage.getItem('lang');
    if (lang == null) lang = 'en';
    const langReq = request.clone({
        headers: request.headers.set('Accept-Language', lang + "-" + lang.toUpperCase())
    });
    return next.handle(langReq);
  }
}
