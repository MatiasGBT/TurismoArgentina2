import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Purchase } from 'src/app/models/purchase';
import { AuthService } from 'src/app/services/auth.service';
import { PurchaseService } from 'src/app/services/purchase.service';

@Component({
  selector: 'ecommerce-account-purchase',
  templateUrl: './purchase.component.html',
  styleUrls: ['./purchase.component.css']
})
export class PurchaseComponent implements OnInit {
  public purchase!: Purchase;

  constructor(private activatedRoute: ActivatedRoute, private purchaseService: PurchaseService,
    private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      if (params['id'] > 0) this.getPurchase(params['id']);
    });
  }

  private getPurchase(id: number): void {
    this.purchaseService.getById(id).subscribe(purchase => {
      if (purchase.user.idUser == this.authService.keycloakUser.idUser) this.purchase = purchase;
      else this.router.navigate(['/shop/account']);
    });
  }
}
