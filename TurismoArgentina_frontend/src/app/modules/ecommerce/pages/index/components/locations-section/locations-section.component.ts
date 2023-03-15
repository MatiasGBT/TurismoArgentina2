import { Component, OnInit } from '@angular/core';
import { Location } from 'src/app/models/location';
import { LocationService } from 'src/app/services/location.service';

@Component({
  selector: 'ecommerce-index-locations-section',
  templateUrl: './locations-section.component.html',
  styleUrls: ['./locations-section.component.css']
})
export class LocationsSectionComponent implements OnInit {
  public locations: Location[] = [];

  constructor(private locationService: LocationService) { }

  ngOnInit(): void {
    this.locationService.getFourRandom().subscribe(locations => this.locations = locations);
  }

  public goToLocation(idLocation: number): void {
    this.locationService.navigateToLocation(idLocation);
  }

}
