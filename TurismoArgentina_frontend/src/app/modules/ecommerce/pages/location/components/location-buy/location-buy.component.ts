import { Component, Input, OnInit } from '@angular/core';
import { Location } from 'src/app/models/location';

@Component({
  selector: 'ecommerce-location-buy',
  templateUrl: './location-buy.component.html',
  styleUrls: ['./location-buy.component.css']
})
export class LocationBuyComponent implements OnInit {
  @Input() location!: Location;

  constructor() { }

  ngOnInit(): void {
    
  }

}
