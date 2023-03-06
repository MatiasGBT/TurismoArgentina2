import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-locations',
  templateUrl: './locations.component.html',
  styleUrls: ['./locations.component.css']
})
export class LocationsComponent implements OnInit {
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
    }
  ]

  constructor() { }

  ngOnInit(): void {
  }

}
