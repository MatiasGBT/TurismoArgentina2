import { Component, OnInit } from '@angular/core';
import { ProvinceService } from 'src/app/services/province.service';

@Component({
  selector: 'ecommerce-index-header',
  templateUrl: './index-header.component.html',
  styleUrls: ['./index-header.component.css']
})
export class IndexHeaderComponent implements OnInit {
  public provinces: any[] = [
    {
      'idProvince': 1,
      'name': 'Buenos Aires',
      'description': '',
      'image': '../../../../../../../assets/img/provinces/bsas.jpg',
      'deletionDate': null
    },
    {
      'idProvince': 2,
      'name': 'Río Negro',
      'description': '',
      'image': '../../../../../../../assets/img/provinces/rio_negro.jpg',
      'deletionDate': null
    },
    {
      'idProvince': 3,
      'name': 'Chubut',
      'description': '',
      'image': '../../../../../../../assets/img/provinces/chubut.jpg',
      'deletionDate': null
    }
  ]

  constructor(private provinceService: ProvinceService) { }

  ngOnInit(): void {
  }

  public goToProvince(idProvince: number): void {
    this.provinceService.navigateToProvince(idProvince);
  }

}
