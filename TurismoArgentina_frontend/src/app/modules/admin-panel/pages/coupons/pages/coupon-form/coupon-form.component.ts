import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Coupon } from 'src/app/models/coupon';
import { CouponService } from 'src/app/services/coupon.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-coupon-form',
  templateUrl: './coupon-form.component.html',
  styleUrls: ['./coupon-form.component.css']
})
export class CouponFormComponent implements OnInit {
  public coupon: Coupon = {} as Coupon;

  constructor(private activatedRoute: ActivatedRoute, private couponService: CouponService,
    private router: Router) {}

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      if (params['id'] > 0) this.getCoupon(params['id']);
    });
  }

  private getCoupon(id: number) {
    this.couponService.getById(id).subscribe(coupon => this.coupon = coupon);
  }

  public saveCoupon(): void {
    this.coupon.idCoupon ? this.updateCoupon() : this.createCoupon();
  }

  public updateCoupon(): void {
    this.couponService.update(this.coupon).subscribe(response => {
      Swal.fire({title: response.message, timer: 1500, timerProgressBar: true, showConfirmButton: false});
      this.router.navigate(['/admin/coupons']);
    });
  }

  public createCoupon(): void {
    this.couponService.create(this.coupon).subscribe(response => {
      Swal.fire({title: response.message, timer: 1500, timerProgressBar: true, showConfirmButton: false});
      this.coupon = response.coupon;
      this.router.navigate(['/admin/coupons']);
    });
  }
}
