import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class ProvinceService {

  constructor(private router: Router) { }

  public navigateToProvince(idProvince: number): void {
    this.router.navigate([`shop/provinces/${idProvince}`]);
  }
}
