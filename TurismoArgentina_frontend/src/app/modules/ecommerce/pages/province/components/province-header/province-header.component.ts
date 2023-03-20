import { Component, Input, OnInit } from '@angular/core';
import { Province } from 'src/app/models/province';

@Component({
  selector: 'ecommerce-province-header',
  templateUrl: './province-header.component.html',
  styleUrls: ['./province-header.component.css']
})
export class ProvinceHeaderComponent implements OnInit {
  @Input() province!: Province;

  constructor() { }

  ngOnInit(): void {
  }

}
