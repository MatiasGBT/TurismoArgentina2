import { Component, OnInit } from '@angular/core';
import { Activity } from 'src/app/models/activity';
import { ActivityService } from 'src/app/services/activity.service';

@Component({
  selector: 'ecommerce-index-activities-section',
  templateUrl: './activities-section.component.html',
  styleUrls: ['./activities-section.component.css']
})
export class ActivitiesSectionComponent implements OnInit {
  public selectedActivity: Activity | undefined;

  public activities: Activity[] = [];

  constructor(private activityService: ActivityService) { }

  ngOnInit(): void {
    this.activityService.getFiveRandom().subscribe(activities => {
      this.selectedActivity = activities.shift();
      this.activities = activities;
    });
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
    if (this.selectedActivity)
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

  public goToActivity(idActivity: number): void {
    this.activityService.navigateToActivity(idActivity);
  }
}
