import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'ecommerce-index-activities-section',
  templateUrl: './activities-section.component.html',
  styleUrls: ['./activities-section.component.css']
})
export class ActivitiesSectionComponent implements OnInit {
  public selectedActivity: any = {
    'idActivity': 1,
    'name': 'Entrada para el Teatro Colón',
    'description': '',
    'image1': '../../../../../../../assets/img/activities/teatro_colon.jpg',
    'image2': null,
    'image3': null,
    'price': 0,
    'duration': 0,
    'deletionDate': null,
    'location': {
      'name': 'Ciudad Autónoma de Buenos Aires'
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
        'name': 'Federación'
      }
    },
  ]

  constructor() { }

  ngOnInit(): void {
  }

  /*
  The selected activity is the one that is displayed larger than the others.
  This method is used so that, when you click on an activity that is not selected,
  it is selected and the old selected activity goes to the list.
  */
  public selectActivity(activity: any, selectedActivityDomElement: HTMLElement): void {
    this.pushSelectedActivityIntoActivities();
    this.removeActivityFromActivities(activity);
    this.setSelectedActivity(activity);
    //On small screens it is necessary to scroll the users so that they can see the
    //change when clicking on an activity.
    if(window.innerWidth < 1038) {
      this.moveToSelectedActivity(selectedActivityDomElement);
    }
  }

  private pushSelectedActivityIntoActivities(): void {
    this.activities.push(this.selectedActivity);
  }

  private removeActivityFromActivities(activity: any): void {
    const index = this.activities.indexOf(activity);
    this.activities.splice(index, 1);
  }

  private setSelectedActivity(activity: any): void {
    this.selectedActivity = activity;
  }

  private moveToSelectedActivity(selectedActivityDomElement: HTMLElement): void {
    selectedActivityDomElement.scrollIntoView({behavior: 'smooth'});
  }
}
