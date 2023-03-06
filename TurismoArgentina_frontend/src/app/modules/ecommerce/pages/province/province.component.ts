import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-province',
  templateUrl: './province.component.html',
  styleUrls: ['./province.component.css']
})
export class ProvinceComponent implements OnInit {
  public province: any = {
    'idProvince': 1,
    'name': 'Buenos Aires',
    'description': 'Buenos Aires es la gran capital cosmopolita de Argentina. Su centro es la Plaza de Mayo, rodeada de imponentes edificios del siglo XIX, incluida la Casa Rosada, el icónico palacio presidencial que tiene varios balcones. Entre otras atracciones importantes, se incluyen el Teatro Colón, un lujoso teatro de ópera de 1908 con cerca de 2,500 asientos, y el moderno museo MALBA, que exhibe arte latinoamericano.',
    'image': '../../../../../../../assets/img/provinces/bsas.jpg',
    'deletionDate': null
  }

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
    }
  ]

  constructor() { }

  ngOnInit(): void {
  }

}
