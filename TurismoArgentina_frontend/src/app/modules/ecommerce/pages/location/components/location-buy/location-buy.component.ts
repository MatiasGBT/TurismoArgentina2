import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'ecommerce-location-buy',
  templateUrl: './location-buy.component.html',
  styleUrls: ['./location-buy.component.css']
})
export class LocationBuyComponent implements OnInit {
  @Input() location: any = {};

  constructor() { }

  ngOnInit(): void {
    
  }

}
