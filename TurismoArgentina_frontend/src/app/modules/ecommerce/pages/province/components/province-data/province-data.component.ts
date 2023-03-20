import { Component, Input, OnInit } from '@angular/core';
import { Province } from 'src/app/models/province';

@Component({
  selector: 'ecommerce-province-data',
  templateUrl: './province-data.component.html',
  styleUrls: ['./province-data.component.css']
})
export class ProvinceDataComponent implements OnInit {
  @Input() province!: Province;

  constructor() { }

  ngOnInit(): void {
  }

}
