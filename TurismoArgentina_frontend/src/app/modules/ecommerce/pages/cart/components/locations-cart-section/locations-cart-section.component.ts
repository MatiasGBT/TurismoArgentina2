import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Location } from 'src/app/models/location';
import { LocationService } from 'src/app/services/location.service';

@Component({
  selector: 'ecommerce-cart-locations-section',
  templateUrl: './locations-cart-section.component.html',
  styleUrls: ['./locations-cart-section.component.css']
})
export class LocationsCartSectionComponent {
  @Input() public locations: Location[] = [];
  @Output() removeLocationEvent = new EventEmitter<number>();

  constructor(private locationService: LocationService) { }

  public getTotalPrice(location: Location): number {
    if (location.peopleQuantity) return location.price * location.peopleQuantity;
    else return location.price;
  }

  public deleteFromCart(location: Location): void {
    this.locationService.deleteFromCart(location.idLocation);
    this.locations = this.locationService.getCartLocations();
    this.removeLocationEvent.emit(this.getTotalPrice(location));
  }
}
