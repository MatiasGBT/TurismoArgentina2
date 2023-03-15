import { Component, OnInit } from '@angular/core';
import { Province } from 'src/app/models/province';
import { ProvinceService } from 'src/app/services/province.service';

@Component({
  selector: 'ecommerce-index-header',
  templateUrl: './index-header.component.html',
  styleUrls: ['./index-header.component.css']
})
export class IndexHeaderComponent implements OnInit {
  public provinces: Province[] = []

  constructor(private provinceService: ProvinceService) { }

  ngOnInit(): void {
    this.provinceService.getThreeRandom().subscribe(provinces => this.provinces = provinces);
  }

  public goToProvince(idProvince: number): void {
    this.provinceService.navigateToProvince(idProvince);
  }

}
