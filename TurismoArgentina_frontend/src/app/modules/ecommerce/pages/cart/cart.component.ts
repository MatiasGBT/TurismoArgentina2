import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Activity } from 'src/app/models/activity';
import { Location } from 'src/app/models/location';
import { Purchase } from 'src/app/models/purchase';
import { ActivityService } from 'src/app/services/activity.service';
import { AuthService } from 'src/app/services/auth.service';
import { LocationService } from 'src/app/services/location.service';
import { PurchaseService } from 'src/app/services/purchase.service';
import { TranslateTextService } from 'src/app/services/translate-text.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  public totalPrice: number = 0;
  public locations: Location[] = [];
  public activities: Activity[] = [];

  constructor(private purchaseService: PurchaseService, 
    private locationService: LocationService,
    private activityService: ActivityService,
    private authService: AuthService,
    private translateTextService: TranslateTextService,
    private router: Router) { }

  ngOnInit(): void {
    this.locations = this.locationService.getCartLocations();
    this.activities = this.activityService.getCartActivities();
    this.getTotalLocationsPrice();
    this.getTotalActivitiesPrice();
    this.getTotalPrice();
  }

  public getTotalPrice(): number {
    return Math.round(this.totalPrice);
  }

  private getTotalLocationsPrice(): void {
    let totalPrice: number = 0;
    this.locations.forEach(location => totalPrice += this.getLocationPrice(location));
    this.totalPrice += totalPrice;
  }

  private getLocationPrice(location: Location): number {
    if (location.peopleQuantity) return location.price * location.peopleQuantity;
    else return location.price;
  }

  public removeLocation(totalLocationPrice: number): void {
    this.locations = this.locationService.getCartLocations();
    this.totalPrice -= totalLocationPrice;
  }

  private getTotalActivitiesPrice(): void {
    let totalPrice: number = 0;
    this.activities.forEach(activity => totalPrice += this.getActivityPrice(activity));
    this.totalPrice += totalPrice;
  }

  private getActivityPrice(activity: Activity): number {
    if (activity.peopleQuantity) return activity.price * activity.peopleQuantity;
    else return activity.price;
  }

  public removeActivity(totalActivityPrice: number): void {
    this.activities = this.activityService.getCartActivities();
    this.totalPrice -= totalActivityPrice;
  }

  public buy(): void {
    if (!this.authService.keycloakUser) this.showBuyErrorModal();
    else this.createPurchase();
  }

  private async showBuyErrorModal(): Promise<void> {
    let title = await this.translateTextService.getTranslatedStringByUrl('GENERAL.UNAUTHORISED_BUY');
    Swal.fire({
      title: title, timer: 2500, timerProgressBar: true,
      showConfirmButton: false
    });
  }

  private createPurchase(): void {
    let purchase: Purchase = this.getPurchase();
    this.purchaseService.create(purchase).subscribe(response => {
      Swal.fire({
        title: response.message, icon: 'success', timer: 2500,
        timerProgressBar: true, showConfirmButton: false
      });
      this.emptyArrays();
      this.router.navigate(['/shop/index']);
    });
  }

  private getPurchase(): Purchase {
    return {
      locations: this.locations,
      activities: this.activities,
      user: this.authService.keycloakUser,
      price: this.totalPrice,
      date: new Date()
    } as Purchase;
  }

  private emptyArrays(): void {
    this.locationService.emptyCartLocations();
    this.activityService.emptyCartActivities();
    this.locations = [];
    this.activities = [];
  }
}
