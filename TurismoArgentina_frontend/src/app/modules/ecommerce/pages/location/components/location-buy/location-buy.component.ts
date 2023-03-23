import { Component, Input, OnInit } from '@angular/core';
import { Location } from 'src/app/models/location';
import { LocationService } from 'src/app/services/location.service';

@Component({
  selector: 'ecommerce-location-buy',
  templateUrl: './location-buy.component.html',
  styleUrls: ['./location-buy.component.css']
})
export class LocationBuyComponent implements OnInit {
  @Input() location!: Location;

  constructor(private locationService: LocationService) { }

  ngOnInit(): void {
  }

  public addToCart(peopleQuantity: number): void {
    this.location.peopleQuantity = peopleQuantity;
    this.locationService.addToCart(this.location);
  }
}
