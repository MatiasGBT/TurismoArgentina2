import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'ecommerce-index-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private translate: TranslateService) { }

  ngOnInit(): void {
    const lang = localStorage.getItem('lang');
    lang ? this.translate.use(lang) : this.translate.use('en');
  }

}
