import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'ecommerce-activity-data',
  templateUrl: './activity-data.component.html',
  styleUrls: ['./activity-data.component.css']
})
export class ActivityDataComponent implements OnInit {
  @Input() activity: any = {};

  constructor() { }

  ngOnInit(): void {
  }

}
