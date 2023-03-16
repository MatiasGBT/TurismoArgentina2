import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Province } from 'src/app/models/province';
import { ProvinceService } from 'src/app/services/province.service';

@Component({
  selector: 'ecommerce-provinces-provinces-section',
  templateUrl: './provinces-section.component.html',
  styleUrls: ['./provinces-section.component.css']
})
export class ProvincesSectionComponent implements OnInit {
  public provinces: Province[] = [];
  public page: number = 0;
  public isFirstPage: boolean = true;
  public isLastPage: boolean = false;
  public totalPages: number = 1;

  constructor(private provinceService: ProvinceService, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      if (params['page']) this.page = params['page'];
      this.getProvinces();
    });
  }

  public goToProvince(idProvince: number): void {
    this.provinceService.navigateToProvince(idProvince);
  }

  public changePage(pageToGo: number) {
    this.page = pageToGo;
    this.provinces = [];
    this.getProvinces();
  }

  private getProvinces() {
    this.provinceService.getAll(this.page).subscribe(response => {
      this.provinces = response.content;
      this.isFirstPage = response.first;
      this.isLastPage = response.last;
      this.totalPages = response.totalPages;
    });
  }
}
