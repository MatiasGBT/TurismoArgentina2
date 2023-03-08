import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'ecommerce-activity-buy',
  templateUrl: './activity-buy.component.html',
  styleUrls: ['./activity-buy.component.css']
})
export class ActivityBuyComponent implements OnInit {
  @Input() activity: any = {};

  constructor() { }

  ngOnInit(): void {
  }

}
