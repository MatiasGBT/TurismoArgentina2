import { Component, OnInit, ViewChild, ElementRef, Renderer2 } from '@angular/core';
import { Router } from '@angular/router';
import { Activity } from 'src/app/models/activity';
import { Location } from 'src/app/models/location';
import { Purchase } from 'src/app/models/purchase';
import { RedeemedCoupon } from 'src/app/models/redeemed-coupon';
import { ActivityService } from 'src/app/services/activity.service';
import { AuthService } from 'src/app/services/auth.service';
import { CouponService } from 'src/app/services/coupon.service';
import { LocationService } from 'src/app/services/location.service';
import { PurchaseService } from 'src/app/services/purchase.service';
import { TranslateTextService } from 'src/app/services/translate-text.service';
import { catchError, throwError } from 'rxjs';
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
  public couponName: string = "";
  private redeemedCoupon: RedeemedCoupon = {} as RedeemedCoupon;
  public couponMessage: string = "";
  public couponError: string = "";
  @ViewChild("couponInput") couponInput!: ElementRef;

  constructor(private purchaseService: PurchaseService, 
    private locationService: LocationService,
    private activityService: ActivityService,
    private authService: AuthService,
    private translateTextService: TranslateTextService,
    private router: Router,
    private couponService: CouponService,
    private renderer2: Renderer2) { }

  ngOnInit(): void {
    this.locations = this.locationService.getCartLocations();
    this.activities = this.activityService.getCartActivities();
    this.setTotalPrice();
  }

  public setTotalPrice(): void {
    this.resetTotalPrice();
    if (this.redeemedCoupon?.coupon?.discount > 0) {
      let discount = this.totalPrice * (this.redeemedCoupon.coupon.discount/100);
      this.totalPrice -= discount;
    }
    this.totalPrice = Math.round(this.totalPrice);
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

  private resetTotalPrice(): void {
    this.totalPrice = 0;
    this.getTotalLocationsPrice();
    this.getTotalActivitiesPrice();
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
    else {
      this.createPurchase();
      this.useCoupon();
    }
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

  private useCoupon(): void {
    if (this.redeemedCoupon?.coupon?.idCoupon) {
      this.redeemedCoupon.isUsed = true;
      this.couponService.updateRedeemedCoupon(this.redeemedCoupon).subscribe(message => console.log(message));
    }
  }

  public verifyInputEnter(event: any): void {
    if (event.key === 'Enter' || event.keyCode == 13) this.redeemCoupon();
  }

  public redeemCoupon(): void {
    if (!this.authService.keycloakUser) this.showBuyErrorModal();
    else {
      this.couponService.redeemCoupon(this.couponName)
        .pipe(
          catchError(ex => {
            this.couponError = ex.error.message;
            this.couponMessage = "";
            this.renderer2.addClass(this.couponInput.nativeElement, "coupon-input-error");
            setTimeout(() => {
              this.renderer2.removeClass(this.couponInput.nativeElement, "coupon-input-error");
            }, 500);
            console.log(ex.error.error);
            this.redeemedCoupon = {} as RedeemedCoupon;
            this.setTotalPrice();
            return throwError(() => ex);
          })
        )
        .subscribe(response => {
          this.couponError = "";
          this.redeemedCoupon = response.redeemedCoupon;
          this.couponMessage = response.message;
          this.setTotalPrice();
        });
    }
  }
}
