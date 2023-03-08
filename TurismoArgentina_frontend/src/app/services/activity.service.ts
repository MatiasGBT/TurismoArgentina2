import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class ActivityService {

  constructor(private router: Router) { }

  public navigateToActivity(idActivity: number): void {
    this.router.navigate([`shop/activities/${idActivity}`]);
  }
}
