import { Component, Input, OnInit } from '@angular/core';
import { Purchase } from 'src/app/models/purchase';
import { AuthService } from 'src/app/services/auth.service';
import { PurchaseService } from 'src/app/services/purchase.service';

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

  constructor(private purchaseService: PurchaseService, private authService: AuthService) {}

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

}
