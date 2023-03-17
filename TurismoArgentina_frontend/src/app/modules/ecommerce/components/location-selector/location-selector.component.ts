import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { LocationService } from 'src/app/services/location.service';

@Component({
  selector: 'ecommerce-location-selector',
  templateUrl: './location-selector.component.html',
  styleUrls: ['./location-selector.component.css']
})
export class LocationSelectorComponent implements OnInit {
  public locationNames: string[] = [];
  public selectedOption: string = 'all';
  @Output() selectLocationEvent = new EventEmitter<string>();

  constructor(private locationService: LocationService) { }

  ngOnInit(): void {
    this.locationService.getAllLocationNames().subscribe(locationNames => this.locationNames = locationNames);
  }

  public selectLocation(): void {
    this.selectLocationEvent.emit(this.selectedOption);
  }
}
