import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'ecommerce-provinces-header',
  templateUrl: './provinces-header.component.html',
  styleUrls: ['./provinces-header.component.css']
})
export class ProvincesHeaderComponent implements OnInit {

  constructor(private translate: TranslateService) { }

  ngOnInit(): void {
    const lang = localStorage.getItem('lang');
    lang ? this.translate.use(lang) : this.translate.use('en');
  }

}
