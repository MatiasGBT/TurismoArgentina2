import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'ecommerce-activities-activities-section',
  templateUrl: './activities-section.component.html',
  styleUrls: ['./activities-section.component.css']
})
export class ActivitiesPageSectionComponent implements OnInit {
  public activities: any[] = [
    {
      'idActivity': 1,
      'name': 'Entrada para el Teatro Colón por Cachaueta',
      'description': '',
      'image1': '../../../../../../../assets/img/activities/teatro_colon.jpg',
      'image2': null,
      'image3': null,
      'price': 0,
      'duration': 0,
      'deletionDate': null,
      'location': {
        'name': 'Ciudad Autónoma de Buenos Aires',
        'province': {
          'name': 'Buenos Aires'
        }
      }
    },
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
        'name': 'San Rafael',
        'province': {
          'name': 'Mendoza'
        }
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
        'name': 'Mendoza Capital',
        'province': {
          'name': 'Mendoza'
        }
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
        'name': 'Puerto Iguazú',
        'province': {
          'name': 'Misiones'
        }
      }
    },
    {
      'idActivity': 5,
      'name': 'Entrada para Termas de Federación',
      'description': '',
      'image1': '../../../../../../../assets/img/activities/termas_federacion.jpg',
      'image2': null,
      'image3': null,
      'price': 0,
      'duration': 0,
      'deletionDate': null,
      'location': {
        'name': 'Federación',
        'province': {
          'name': 'Entre Ríos'
        }
      }
    },
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
        'name': 'San Rafael',
        'province': {
          'name': 'Mendoza'
        }
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
        'name': 'Mendoza Capital',
        'province': {
          'name': 'Mendoza'
        }
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
        'name': 'Puerto Iguazú',
        'province': {
          'name': 'Misiones'
        }
      }
    },
    {
      'idActivity': 5,
      'name': 'Entrada para Termas de Federación',
      'description': '',
      'image1': '../../../../../../../assets/img/activities/termas_federacion.jpg',
      'image2': null,
      'image3': null,
      'price': 0,
      'duration': 0,
      'deletionDate': null,
      'location': {
        'name': 'Federación',
        'province': {
          'name': 'Entre Ríos'
        }
      }
    },
  ]

  constructor() { }

  ngOnInit(): void {
  }

}
