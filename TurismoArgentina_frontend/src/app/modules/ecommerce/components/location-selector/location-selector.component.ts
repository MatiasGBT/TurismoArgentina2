import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { LocationService } from 'src/app/services/location.service';

@Component({
  selector: 'ecommerce-location-selector',
  templateUrl: './location-selector.component.html',
  styleUrls: ['./location-selector.component.css']
})
export class LocationSelectorComponent implements OnInit {
  public locationNames: string[] = [];
  public selectedOption: string = '0';
  @Output() selectLocationEvent = new EventEmitter<number>();

  constructor(private locationService: LocationService) { }

  ngOnInit(): void {
    //As the API documentation says, the location names come with
    //their ID interposed, so the view uses the split method to
    //show only the names or send the id as needed.
    this.locationService.getAllLocationNames().subscribe(locationNames => this.locationNames = locationNames);
  }

  public selectLocation(): void {
    this.selectLocationEvent.emit(parseInt(this.selectedOption));
  }
}
