import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'ecommerce-location-data',
  templateUrl: './location-data.component.html',
  styleUrls: ['./location-data.component.css']
})
export class LocationDataComponent implements OnInit {
  @Input() location: any = {};

  constructor() { }

  ngOnInit(): void {
  }

}
