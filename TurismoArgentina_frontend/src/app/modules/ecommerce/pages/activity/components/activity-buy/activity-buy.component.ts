import { Component, Input, OnInit } from '@angular/core';
import { Activity } from 'src/app/models/activity';
import { ActivityService } from 'src/app/services/activity.service';

@Component({
  selector: 'ecommerce-activity-buy',
  templateUrl: './activity-buy.component.html',
  styleUrls: ['./activity-buy.component.css']
})
export class ActivityBuyComponent implements OnInit {
  @Input() activity!: Activity;

  constructor(private activityService: ActivityService) { }

  ngOnInit(): void {
  }

  public addToCart(peopleQuantity: number): void {
    this.activity.peopleQuantity = peopleQuantity;
    this.activityService.addToCart(this.activity);
  }
}
