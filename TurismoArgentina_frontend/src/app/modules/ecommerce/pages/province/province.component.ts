import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from 'src/app/models/location';
import { Province } from 'src/app/models/province';
import { LocationService } from 'src/app/services/location.service';
import { ProvinceService } from 'src/app/services/province.service';

@Component({
  selector: 'app-province',
  templateUrl: './province.component.html',
  styleUrls: ['./province.component.css']
})
export class ProvinceComponent implements OnInit {
  public province: Province | undefined;
  public locations: Location[] = [];

  constructor(private activatedRoute: ActivatedRoute, private provinceService: ProvinceService,
    private locationService: LocationService) { }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      if (params['id']) {
        this.getProvince(params['id']);
        this.getLocations(params['id']);
      }
    });
  }

  private getProvince(idProvince: number): void {
    this.provinceService.getById(idProvince).subscribe(province => this.province = province);
  }

  private getLocations(idProvince: number): void {
    this.locationService.getByProvinceId(idProvince).subscribe(locations => this.locations = locations);
  }
}
