import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'ecommerce-province-data',
  templateUrl: './province-data.component.html',
  styleUrls: ['./province-data.component.css']
})
export class ProvinceDataComponent implements OnInit {
  @Input() province: any = {};

  constructor() { }

  ngOnInit(): void {
  }

}
