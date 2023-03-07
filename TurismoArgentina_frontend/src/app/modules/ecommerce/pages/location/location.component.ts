import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-location',
  templateUrl: './location.component.html',
  styleUrls: ['./location.component.css']
})
export class LocationComponent implements OnInit {
  public location: any = {
    'idLocation': 2,
    'name': 'Tigre',
    'description': 'A tan solo un día de viaje de Buenos Aires, el delta del Río Tigre les ofrece tanto a los porteños como a los turistas un espacio ideal para realizar una gran variedad de actividades que van desde montar a caballo y hacer senderismo hasta pescar y nadar.',
    'image': '../../../../../../../assets/img/locations/tigre.jpg',
    'price': 6829,
    'deletionDate': null,
    'province': {
      'idProvince': 1,
      'name': 'Buenos Aires'
    }
  }

  public activities: any[] = [
    {
      'idActivity': 2,
      'name': 'Tour por el cañón del Atuel',
      'description': '',
      'image1': '../../../../../../../assets/img/activities/canon_del_atuel.jpg',
      'image2': null,
      'image3': null,
      'price': 0,
      'duration': 0,
      'deletionDate': null,
      'location': {
        'name': 'San Rafael'
      }
    },
    {
      'idActivity': 3,
      'name': 'Día de spa en las aguas termales de Cacheuta',
      'description': '',
      'image1': '../../../../../../../assets/img/activities/spa.jpg',
      'image2': null,
      'image3': null,
      'price': 0,
      'duration': 0,
      'deletionDate': null,
      'location': {
        'name': 'Mendoza Capital'
      }
    },
    {
      'idActivity': 4,
      'name': 'Aventura Privada de Tirolina y Rappel',
      'description': '',
      'image1': '../../../../../../../assets/img/activities/tirolina_y_rapel.jpg',
      'image2': null,
      'image3': null,
      'price': 0,
      'duration': 0,
      'deletionDate': null,
      'location': {
        'name': 'Puerto Iguazú'
      }
    }
  ]

  constructor() { }

  ngOnInit(): void {
  }

}
