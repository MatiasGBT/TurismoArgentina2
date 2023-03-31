import { Component, OnInit } from '@angular/core';
import { ActivityService } from 'src/app/services/activity.service';
import { LocationService } from 'src/app/services/location.service';
import { ProvinceService } from 'src/app/services/province.service';
import { PurchaseService } from 'src/app/services/purchase.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  public usersCount: number | undefined;
  public nonRefundedPurchasesCount: number | undefined;
  public refundedPurchasesCount: number | undefined;
  public nonRefundedMoney: number | undefined;
  public refundedMoney: number | undefined;
  public provincesCount: number | undefined;
  public locationsCount: number | undefined;
  public activitiesCount: number | undefined;

  constructor(private provinceService: ProvinceService, private locationService: LocationService,
    private activityService: ActivityService, private userService: UserService,
    private purchaseService: PurchaseService) { }

  ngOnInit(): void {
    this.getNonRefundedPurchasesCount();
    this.getRefundedPurchasesCount();
    this.getNonRefundedMoney();
    this.getRefundedMoney();
    this.getUsersCount();
    this.getProvincesCount();
    this.getLocationsCount();
    this.getActivitiesCount();
  }

  private getNonRefundedPurchasesCount(): void {
    this.purchaseService.getCount(false).subscribe(count => this.nonRefundedPurchasesCount = count);
  }

  private getRefundedPurchasesCount(): void {
    this.purchaseService.getCount(true).subscribe(count => this.refundedPurchasesCount = count);
  }

  private getNonRefundedMoney(): void {
    this.purchaseService.getMoney(false).subscribe(count => this.nonRefundedMoney = count);
  }

  private getRefundedMoney(): void {
    this.purchaseService.getMoney(true).subscribe(count => this.refundedMoney = count);
  }

  private getUsersCount(): void {
    this.userService.getCount().subscribe(count => this.usersCount = count);
  }

  private getProvincesCount(): void {
    this.provinceService.getCount().subscribe(count => this.provincesCount = count);
  }

  private getLocationsCount(): void {
    this.locationService.getCount().subscribe(count => this.locationsCount = count);
  }

  private getActivitiesCount(): void {
    this.activityService.getCount().subscribe(count => this.activitiesCount = count);
  }
}
