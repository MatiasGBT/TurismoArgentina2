import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Location } from 'src/app/models/location';
import { LocationService } from 'src/app/services/location.service';
import { TranslateTextService } from 'src/app/services/translate-text.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'ecommerce-location-buy',
  templateUrl: './location-buy.component.html',
  styleUrls: ['./location-buy.component.css']
})
export class LocationBuyComponent implements OnInit {
  @Input() location!: Location;

  constructor(private locationService: LocationService, private router: Router,
    private translateTextService: TranslateTextService) { }

  ngOnInit(): void {
  }

  public addToCart(peopleQuantity: number): void {
    this.location.peopleQuantity = peopleQuantity;
    let noError: boolean = this.locationService.addToCart(this.location);
    if (noError) this.fireNoErrorModal();
  }

  private async fireNoErrorModal(): Promise<void> {
    const title: string = await this.translateTextService.getTranslatedStringByUrl('ECOMMERCE.LOCATION.BUY.ADDED');
    const confirmBtnText: string = await this.translateTextService.getTranslatedStringByUrl('ECOMMERCE.LOCATION.BUY.GO_TO');
    const cancelBtnText: string = await this.translateTextService.getTranslatedStringByUrl('ECOMMERCE.LOCATION.BUY.CONTINUE');
    Swal.fire({
      title: title, confirmButtonText: confirmBtnText, cancelButtonText: cancelBtnText,
      confirmButtonColor: '#2F80ED', cancelButtonColor: '#56CCF2', showCancelButton: true,
    }).then((result) => {
      if (result.isConfirmed) this.router.navigate(['/shop/cart']);
    });
  }
}
