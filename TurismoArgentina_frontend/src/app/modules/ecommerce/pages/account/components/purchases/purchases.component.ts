import { Component, Input, OnInit } from '@angular/core';
import { Purchase } from 'src/app/models/purchase';
import { AuthService } from 'src/app/services/auth.service';
import { PurchaseService } from 'src/app/services/purchase.service';
import { TranslateTextService } from 'src/app/services/translate-text.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'ecommerce-account-purchases',
  templateUrl: './purchases.component.html',
  styleUrls: ['./purchases.component.css']
})
export class PurchasesComponent implements OnInit {
  public purchases: Purchase[] = [];
  public page: number = 0;
  public isFirstPage: boolean = true;
  public isLastPage: boolean = false;
  public totalPages: number = 1;
  @Input() public userIsLoggedIn: boolean = false;
  public purchasesAreLoaded: boolean = false;

  constructor(private purchaseService: PurchaseService, private authService: AuthService,
    private translateTextService: TranslateTextService) {}

  ngOnInit(): void {
    if (this.authService.keycloakUser) this.getPurchases();
    else this.purchasesAreLoaded = true;
  }

  public changePage(pageToGo: number): void {
    this.page = pageToGo;
    this.purchases = [];
    this.purchasesAreLoaded = false;
    this.getPurchases();
  }

  private getPurchases(): void {
    this.purchaseService.getByUser(this.authService.keycloakUser.idUser, this.page).subscribe(response => {
      this.purchases = response.content;
      this.isFirstPage = response.first;
      this.isLastPage = response.last;
      this.totalPages = response.totalPages;
      if (!this.purchasesAreLoaded) this.purchasesAreLoaded = true;
    });
  }

  public async askForRefund(purchase: Purchase): Promise<void> {
    const title: string = await this.translateTextService.getTranslatedStringByUrl('ECOMMERCE.ACCOUNT.REFUND.TITLE');
    const text: string = await this.translateTextService.getTranslatedStringByUrl('ECOMMERCE.ACCOUNT.REFUND.TEXT');
    const confirmBtnText: string = await this.translateTextService.getTranslatedStringByUrl('ECOMMERCE.ACCOUNT.REFUND.CONFIRM_BTN');
    const cancelBtnText: string = await this.translateTextService.getTranslatedStringByUrl('ECOMMERCE.ACCOUNT.REFUND.CANCEL_BTN');
    Swal.fire({
      title: title, text: text, confirmButtonText: confirmBtnText, cancelButtonText: cancelBtnText,
      confirmButtonColor: '#e53170', cancelButtonColor: '#2F80ED', showCancelButton: true, icon: 'warning'
    }).then((result) => {
      if (result.isConfirmed) {
        purchase.isRefunded = true;
        this.refundPurchase(purchase);
      }
    });
  }

  private refundPurchase(purchase: Purchase): void {
    this.purchaseService.update(purchase).subscribe(response => {
      Swal.fire({title: response.message, timer: 2000, timerProgressBar: true, showConfirmButton: false});
    })
  }
}
