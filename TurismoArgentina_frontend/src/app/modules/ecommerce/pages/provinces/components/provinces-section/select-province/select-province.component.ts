import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'ecommerce-provinces-provinces-section-select-province',
  templateUrl: './select-province.component.html',
  styleUrls: ['./select-province.component.css']
})
export class SelectProvinceComponent implements OnInit {
  public provincesNames: string[] = [
    "Buenos Aires",
    "Río Negro",
    "Chaco",
    "Buenos Aires",
    "Río Negro",
    "Chaco",
    "Buenos Aires",
    "Río Negro",
    "Chaco"
  ]

  constructor() { }

  ngOnInit(): void {
  }

  public goToProvincePage(): void {
    console.log("unsupported")
  }
}
