import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'ecommerce-location-header',
  templateUrl: './location-header.component.html',
  styleUrls: ['./location-header.component.css']
})
export class LocationHeaderComponent implements OnInit {
  @Input() location: any = {};

  constructor() { }

  ngOnInit(): void {
  }

}
