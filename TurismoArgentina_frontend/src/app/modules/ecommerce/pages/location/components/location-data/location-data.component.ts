import { Component, Input, OnInit } from '@angular/core';
import { Location } from 'src/app/models/location';

@Component({
  selector: 'ecommerce-location-data',
  templateUrl: './location-data.component.html',
  styleUrls: ['./location-data.component.css']
})
export class LocationDataComponent implements OnInit {
  @Input() location!: Location;

  constructor() { }

  ngOnInit(): void {
  }

}
