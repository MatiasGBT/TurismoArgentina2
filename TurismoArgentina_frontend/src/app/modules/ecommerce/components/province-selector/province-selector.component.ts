import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ProvinceService } from 'src/app/services/province.service';

@Component({
  selector: 'ecommerce-province-selector',
  templateUrl: './province-selector.component.html',
  styleUrls: ['./province-selector.component.css']
})
export class ProvinceSelectorComponent implements OnInit {
  public provinceNames: string[] = [];
  public selectedOption: string = 'all';
  @Output() selectProvinceEvent = new EventEmitter<string>();

  constructor(private provinceService: ProvinceService) { }

  ngOnInit(): void {
    this.provinceService.getAllProvinceNames().subscribe(provinceNames => this.provinceNames = provinceNames);
  }

  public selectProvince(): void {
    this.selectProvinceEvent.emit(this.selectedOption);
  }
}
