import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ProvinceService } from 'src/app/services/province.service';

@Component({
  selector: 'ecommerce-province-selector',
  templateUrl: './province-selector.component.html',
  styleUrls: ['./province-selector.component.css']
})
export class ProvinceSelectorComponent implements OnInit {
  public provinceNames: string[] = [];
  public selectedOption: string = 'default';
  @Output() selectProvinceEvent = new EventEmitter<number>();

  constructor(private provinceService: ProvinceService) { }

  ngOnInit(): void {
    //As the API documentation says, the province names come with
    //their ID interposed, so the view uses the split method to
    //show only the names or send the id as needed.
    this.provinceService.getAllProvinceNames().subscribe(provinceNames => this.provinceNames = provinceNames);
  }

  public selectProvince(): void {
    this.selectProvinceEvent.emit(parseInt(this.selectedOption));
  }
}
