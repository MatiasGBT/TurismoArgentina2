import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Activity } from 'src/app/models/activity';
import { ActivityService } from 'src/app/services/activity.service';
import { TranslateTextService } from 'src/app/services/translate-text.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'ecommerce-activity-buy',
  templateUrl: './activity-buy.component.html',
  styleUrls: ['./activity-buy.component.css']
})
export class ActivityBuyComponent implements OnInit {
  @Input() activity!: Activity;

  constructor(private activityService: ActivityService, private router: Router,
    private translateTextService: TranslateTextService) { }

  ngOnInit(): void {
  }

  public addToCart(peopleQuantity: number): void {
    this.activity.peopleQuantity = peopleQuantity;
    let noError:boolean = this.activityService.addToCart(this.activity);
    if (noError) this.fireNoErrorModal();
  }

  private async fireNoErrorModal(): Promise<void> {
    const title: string = await this.translateTextService.getTranslatedStringByUrl('ECOMMERCE.ACTIVITY.BUY.ADDED');
    const confirmBtnText: string = await this.translateTextService.getTranslatedStringByUrl('ECOMMERCE.ACTIVITY.BUY.GO_TO');
    const cancelBtnText: string = await this.translateTextService.getTranslatedStringByUrl('ECOMMERCE.ACTIVITY.BUY.CONTINUE');
    Swal.fire({
      title: title, confirmButtonText: confirmBtnText, cancelButtonText: cancelBtnText,
      confirmButtonColor: '#2F80ED', cancelButtonColor: '#56CCF2', showCancelButton: true,
    }).then((result) => {
      if (result.isConfirmed) this.router.navigate(['/shop/cart']);
    });
  }
}
