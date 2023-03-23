import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'ecommerce-buy-form',
  templateUrl: './buy-form.component.html',
  styleUrls: ['./buy-form.component.css']
})
export class BuyFormComponent implements OnInit {
  public peopleQuantity: number = 1;
  public addToCartButton: string = '';
  @Output() addToCartEvent = new EventEmitter<number>();

  constructor(private translate: TranslateService) { }

  ngOnInit(): void {
    this.translate.get('ECOMMERCE.BUY_FORM.SEND_BUTTON').subscribe(response => this.addToCartButton = response);
  }

  public addToCart(): void {
    this.addToCartEvent.emit(this.peopleQuantity);
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
