import { Component, Input, OnInit } from '@angular/core';
import { Location } from 'src/app/models/location';
import { LocationService } from 'src/app/services/location.service';

@Component({
  selector: 'ecommerce-province-locations',
  templateUrl: './province-locations.component.html',
  styleUrls: ['./province-locations.component.css']
})
export class ProvinceLocationsComponent implements OnInit {
  @Input() locations: Location[] = [];

  constructor(private locationService: LocationService) { }

  ngOnInit(): void {
  }

  public goToLocation(idLocation: number): void {
    this.locationService.navigateToLocation(idLocation);
  }

}
