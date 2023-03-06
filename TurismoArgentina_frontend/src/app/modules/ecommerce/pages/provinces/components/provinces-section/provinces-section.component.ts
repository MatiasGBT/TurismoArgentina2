import { Component, OnInit } from '@angular/core';
import { ProvinceService } from 'src/app/services/province.service';

@Component({
  selector: 'ecommerce-provinces-provinces-section',
  templateUrl: './provinces-section.component.html',
  styleUrls: ['./provinces-section.component.css']
})
export class ProvincesSectionComponent implements OnInit {
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
    },
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
    },
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
