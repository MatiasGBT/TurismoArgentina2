import { Component, Input, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'ecommerce-location-buy',
  templateUrl: './location-buy.component.html',
  styleUrls: ['./location-buy.component.css']
})
export class LocationBuyComponent implements OnInit {
  @Input() location: any = {};
  public peopleQuantity: number = 1;
  public addToCartButton: string = '';

  constructor(private translate: TranslateService) { }

  ngOnInit(): void {
    this.translate.get('ECOMMERCE.LOCATION.BUY.FORM.SEND_BUTTON').subscribe(response => this.addToCartButton = response);
  }

  public addToCart(): void {
    console.log("unsupported");
  }

  public reducePeopleQuantity(): void {
    if (this.peopleQuantity > 1)
      this.peopleQuantity -= 1;
  }

  public increasePeopleQuantity(): void {
    if (this.peopleQuantity < 6)
      this.peopleQuantity += 1;
  }

  public verifyValues(): void {
    if (this.peopleQuantity < 1)
      this.peopleQuantity = 1;
    if (this.peopleQuantity > 6)
      this.peopleQuantity = 6;
  }

}
