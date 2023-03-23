import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  public totalPrice: number = 0;
  public buyFormPlaceholder: string = "";

  constructor(private translate: TranslateService) { }

  ngOnInit(): void {
    this.translate.get('ECOMMERCE.CART.BUY_FORM.COUPON').subscribe(response => this.buyFormPlaceholder = response);
  }

  public getTotalPrice(): number {
    return Math.round(this.totalPrice);
  }

  public getTotalLocationsPrice(totalLocationPrice: number): void {
    this.totalPrice += totalLocationPrice;
  }

  public removeTotalLocationPrice(totalLocationPrice: number): void {
    this.totalPrice -= totalLocationPrice;
  }

  public getTotalActivitiesPrice(totalActivityPrice: number): void {
    this.totalPrice += totalActivityPrice;
  }

  public removeTotalActivityPrice(totalActivityPrice: number): void {
    this.totalPrice -= totalActivityPrice;
  }
}
