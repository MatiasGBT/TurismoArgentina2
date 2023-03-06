import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'ecommerce-province-locations',
  templateUrl: './province-locations.component.html',
  styleUrls: ['./province-locations.component.css']
})
export class ProvinceLocationsComponent implements OnInit {
  @Input() locations: any[] = [];

  constructor() { }

  ngOnInit(): void {
  }

}
