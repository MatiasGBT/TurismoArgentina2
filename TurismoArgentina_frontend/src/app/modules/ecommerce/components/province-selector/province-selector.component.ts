import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'ecommerce-province-selector',
  templateUrl: './province-selector.component.html',
  styleUrls: ['./province-selector.component.css']
})
export class ProvinceSelectorComponent implements OnInit {
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

  public selectProvince(): void {
    console.log("unsupported")
  }
}
