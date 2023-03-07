import { Component, OnInit } from '@angular/core';
import { LocationService } from 'src/app/services/location.service';

@Component({
  selector: 'ecommerce-index-locations-section',
  templateUrl: './locations-section.component.html',
  styleUrls: ['./locations-section.component.css']
})
export class LocationsSectionComponent implements OnInit {
  public locations: any[] = [
    {
      'idLocation': 1,
      'name': 'Puerto Iguazú',
      'description': '',
      'image': '../../../../../../../assets/img/locations/puerto_iguazu.jpg',
      'price': 0,
      'deletionDate': null,
      'province': {
        'name': 'Misiones'
      }
    },
    {
      'idLocation': 2,
      'name': 'Tigre',
      'description': '',
      'image': '../../../../../../../assets/img/locations/tigre.jpg',
      'price': 0,
      'deletionDate': null,
      'province': {
        'name': 'Buenos Aires'
      }
    },
    {
      'idLocation': 3,
      'name': 'Puerto Madryn',
      'description': '',
      'image': '../../../../../../../assets/img/locations/puerto_madryn.jpg',
      'price': 0,
      'deletionDate': null,
      'province': {
        'name': 'Chubut'
      }
    },
    {
      'idLocation': 4,
      'name': 'Cuevas de Acsibi',
      'description': '',
      'image': '../../../../../../../assets/img/locations/cuevas_de_acsibi.jpg',
      'price': 0,
      'deletionDate': null,
      'province': {
        'name': 'Salta'
      }
    },
  ]

  constructor(private locationService: LocationService) { }

  ngOnInit(): void {
  }

  public goToLocation(idLocation: number): void {
    this.locationService.navigateToLocation(idLocation);
  }

}
