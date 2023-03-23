import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Location } from 'src/app/models/location';
import { LocationService } from 'src/app/services/location.service';

@Component({
  selector: 'ecommerce-cart-locations-section',
  templateUrl: './locations-cart-section.component.html',
  styleUrls: ['./locations-cart-section.component.css']
})
export class LocationsCartSectionComponent implements OnInit {
  public locations: Location[] = [];
  @Output() totalLocationsPriceEvent = new EventEmitter<number>();
  @Output() removeLocationEvent = new EventEmitter<number>();

  constructor(private locationService: LocationService) { }

  ngOnInit(): void {
    this.locations = this.locationService.getCartLocations();
    this.emitTotalLocationsPrice();
  }

  private emitTotalLocationsPrice(): void {
    let totalPrice: number = 0;
    this.locations.forEach(location => totalPrice += this.getTotalPrice(location));
    this.totalLocationsPriceEvent.emit(totalPrice);
  }

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
