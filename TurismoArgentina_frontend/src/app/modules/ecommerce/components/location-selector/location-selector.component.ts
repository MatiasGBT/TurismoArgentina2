import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'ecommerce-location-selector',
  templateUrl: './location-selector.component.html',
  styleUrls: ['./location-selector.component.css']
})
export class LocationSelectorComponent implements OnInit {
  public locationsNames: string[] = [
    "Puerto Iguazú",
    "Tigre",
    "Puerto Madryn",
    "Cuevas de Acsibi",
    "Puerto Iguazú",
    "Tigre",
    "Puerto Madryn",
    "Puerto Madryn"
  ]

  constructor() { }

  ngOnInit(): void {
  }

  public selectLocation(): void {
    console.log("unsupported")
  }
}
