import { Component, Input, OnInit } from '@angular/core';
import { Activity } from 'src/app/models/activity';

@Component({
  selector: 'ecommerce-activity-buy',
  templateUrl: './activity-buy.component.html',
  styleUrls: ['./activity-buy.component.css']
})
export class ActivityBuyComponent implements OnInit {
  @Input() activity!: Activity;

  constructor() { }

  ngOnInit(): void {
  }

}
