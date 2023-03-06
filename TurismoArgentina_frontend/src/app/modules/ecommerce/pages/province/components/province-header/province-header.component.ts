import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'ecommerce-province-header',
  templateUrl: './province-header.component.html',
  styleUrls: ['./province-header.component.css']
})
export class ProvinceHeaderComponent implements OnInit {
  @Input() province: any = {};

  constructor() { }

  ngOnInit(): void {
  }

}
