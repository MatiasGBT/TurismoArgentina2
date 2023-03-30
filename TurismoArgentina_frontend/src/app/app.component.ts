import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  
  constructor(private translate: TranslateService) {
    translate.addLangs(['en', 'es', 'pt']);
    translate.setDefaultLang('en');
  }

  ngOnInit(): void {
    const lang = localStorage.getItem('lang');
    lang ? this.translate.use(lang) : this.translate.use('en');
  }
}
