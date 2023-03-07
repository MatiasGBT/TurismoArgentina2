import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  constructor(private router: Router) { }

  public navigateToLocation(idLocation: number): void {
    this.router.navigate([`shop/locations/${idLocation}`]);
  }
}
