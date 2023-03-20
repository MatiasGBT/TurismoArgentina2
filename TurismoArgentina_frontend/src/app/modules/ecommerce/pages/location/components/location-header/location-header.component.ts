import { Component, Input, OnInit } from '@angular/core';
import { Location } from 'src/app/models/location';

@Component({
  selector: 'ecommerce-location-header',
  templateUrl: './location-header.component.html',
  styleUrls: ['./location-header.component.css']
})
export class LocationHeaderComponent implements OnInit {
  @Input() location!: Location;

  constructor() { }

  ngOnInit(): void {
  }

}
