import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'ecommerce-location-activities',
  templateUrl: './location-activities.component.html',
  styleUrls: ['./location-activities.component.css']
})
export class LocationActivitiesComponent implements OnInit {
  @Input() activities: any[] = [];

  constructor() { }

  ngOnInit(): void {
  }

}
