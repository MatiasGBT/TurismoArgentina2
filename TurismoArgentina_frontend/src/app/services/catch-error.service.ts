import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CatchErrorService {

  constructor() { }

  public showError(ex: any): void {
    console.log("Error: " + ex.error.title + "\n" + ex.error.message);
  }
}
