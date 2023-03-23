import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Activity } from 'src/app/models/activity';
import { ActivityService } from 'src/app/services/activity.service';

@Component({
  selector: 'ecommerce-cart-activities-section',
  templateUrl: './activities-cart-section.component.html',
  styleUrls: ['./activities-cart-section.component.css']
})
export class ActivitiesCartSectionComponent implements OnInit {
  public activities: Activity[] = [];
  @Output() totalActivitiesPrice = new EventEmitter<number>();
  @Output() removeActivityEvent = new EventEmitter<number>();

  constructor(private activityService: ActivityService) { }

  ngOnInit(): void {
    this.activities = this.activityService.getCartActivities();
    this.emitTotalActivitiesPrice();
  }

  private emitTotalActivitiesPrice(): void {
    let totalPrice: number = 0;
    this.activities.forEach(activity => totalPrice += this.getTotalPrice(activity));
    this.totalActivitiesPrice.emit(totalPrice);
  }

  public getTotalPrice(activity: Activity): number {
    if (activity.peopleQuantity) return activity.price * activity.peopleQuantity;
    else return activity.price;
  }

  public deleteFromCart(activity: Activity): void {
    this.activityService.deleteFromCart(activity.idActivity);
    this.activities = this.activityService.getCartActivities();
    this.removeActivityEvent.emit(this.getTotalPrice(activity));
  }
}
