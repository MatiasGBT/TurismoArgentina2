import { Component, OnInit } from '@angular/core';
import { Coupon } from 'src/app/models/coupon';
import { CouponService } from 'src/app/services/coupon.service';
import { TranslateTextService } from 'src/app/services/translate-text.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'admin-coupons',
  templateUrl: './coupons.component.html',
  styleUrls: ['./coupons.component.css']
})
export class CouponsComponent implements OnInit {
  public coupons: Coupon[] = [];
  public page: number = 0;
  public isFirstPage: boolean = true;
  public isLastPage: boolean = false;
  public totalPages: number = 1;
  public couponsAreLoaded: boolean = false;
  private filteredByName: boolean = false;
  private couponName: string = "";

  constructor(private couponService: CouponService,
    private translateTextService: TranslateTextService) { }

  ngOnInit(): void {
    this.getCoupons();
  }

  public changePage(pageToGo: number) {
    this.page = pageToGo;
    this.couponsAreLoaded = false;
    this.coupons = [];
    this.filteredByName ? this.getCouponsByName() : this.getCoupons();
  }

  private getCoupons() {
    this.couponService.getAll(this.page).subscribe(response => {
      this.setPropertiesWithResponse(response);
    });
  }

  private getCouponsByName() {
    this.couponService.getAllByName(this.page, this.couponName).subscribe(response => {
      this.setPropertiesWithResponse(response);
    });
  }

  private setPropertiesWithResponse(response: any): void {
    this.coupons = response.content;
    this.isFirstPage = response.first;
    this.isLastPage = response.last;
    this.totalPages = response.totalPages;
    this.couponsAreLoaded = true;
  }

  public search(name: string): void {
    if (name.length <= 45) {
      this.couponName = name;
      this.filteredByName = true;
    }
    this.changePage(0);
  }

  public resetSearch(): void {
    this.couponName = "";
    this.filteredByName = false;
    this.changePage(0);
  }

  public async showDeleteModal(coupon: Coupon): Promise<void> {
    const title: string = await this.translateTextService.getTranslatedStringByUrl('ADMIN_PANEL.COUPONS.DELETE_MODAL.TITLE');
    const text: string = await this.translateTextService.getTranslatedStringByUrl('ADMIN_PANEL.COUPONS.DELETE_MODAL.TEXT');
    const confirmBtnText: string = await this.translateTextService.getTranslatedStringByUrl('ADMIN_PANEL.COUPONS.DELETE_MODAL.CONFIRM_BTN');
    const cancelBtnText: string = await this.translateTextService.getTranslatedStringByUrl('ADMIN_PANEL.COUPONS.DELETE_MODAL.CANCEL_BTN');
    Swal.fire({
      title: title, text: text, confirmButtonText: confirmBtnText, cancelButtonText: cancelBtnText,
      confirmButtonColor: '#e53170', cancelButtonColor: '#2F80ED', showCancelButton: true, icon: 'warning'
    }).then((result) => {
      if (result.isConfirmed) {
        this.deleteCoupon(coupon);
      }
    });
  }

  private deleteCoupon(coupon: Coupon): void {
    this.couponService.delete(coupon.idCoupon).subscribe(response => this.showConfirmationModal(response.message));
  }

  private showConfirmationModal(message: string): void {
    Swal.fire({
      title: message, showConfirmButton: false,
      timer: 1500, timerProgressBar: true
    }).then(() => window.location.reload());
  }
}
