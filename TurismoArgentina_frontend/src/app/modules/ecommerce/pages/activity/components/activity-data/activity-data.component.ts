import { Component, Input, OnInit } from '@angular/core';
import { Activity } from 'src/app/models/activity';

@Component({
  selector: 'ecommerce-activity-data',
  templateUrl: './activity-data.component.html',
  styleUrls: ['./activity-data.component.css']
})
export class ActivityDataComponent implements OnInit {
  @Input() activity!: Activity;

  constructor() { }

  ngOnInit(): void {
  }

}
