import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-activity',
  templateUrl: './activity.component.html',
  styleUrls: ['./activity.component.css']
})
export class ActivityComponent implements OnInit {
  public activity: any = {
    'idActivity': 1,
    'name': 'Entrada para el Teatro Colón',
    'description': 'Para los fanáticos culturales, ninguna visita a Buenos Aires estaría completa sin visitar el Teatro Colón. Ahorre tiempo de espera con una entrada reservada con antelación que incluye una visita guiada al magnífico edificio. Explorar con un guía significa que obtendrá información sobre la historia y la arquitectura del monumento.',
    'image1': '../../../../../../../assets/img/activities/teatro_colon.jpg',
    'image2': '../../../../../../../assets/img/activities/teatro_colon.jpg',
    'image3': '../../../../../../../assets/img/activities/teatro_colon.jpg',
    'price': 7204.61,
    'duration': 1,
    'deletionDate': null,
    'location': {
      'idLocation': 1,
      'name': 'Ciudad Autónoma de Buenos Aires'
    }
  }

  constructor() { }

  ngOnInit(): void {
  }

}
