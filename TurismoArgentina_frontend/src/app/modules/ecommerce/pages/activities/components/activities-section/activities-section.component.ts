import { Component, OnInit } from '@angular/core';
import { Activity } from 'src/app/models/activity';
import { ActivityService } from 'src/app/services/activity.service';

@Component({
  selector: 'ecommerce-activities-activities-section',
  templateUrl: './activities-section.component.html',
  styleUrls: ['./activities-section.component.css']
})
export class ActivitiesPageSectionComponent implements OnInit {
  public activities: Activity[] = [];
  public page: number = 0;
  public isFirstPage: boolean = true;
  public isLastPage: boolean = false;
  public totalPages: number = 1;
  private sortedByLocation: boolean = false;
  private locationName: string = 'all';

  constructor(private activityService: ActivityService) { }

  ngOnInit(): void {
    this.getActivities();
  }

  public goToActivity(idActivity: number): void {
    this.activityService.navigateToActivity(idActivity);
  }

  public changePage(pageToGo: number): void {
    this.page = pageToGo;
    this.activities = [];
    this.getActivities();
  }

  private getActivities(): void {
    if (!this.sortedByLocation) this.getAllActivities();
    else this.getSortedByLocationName(this.locationName);
  }

  private getAllActivities(): void {
    this.activityService.getAll(this.page, false).subscribe(response => {
      this.setPublicVariables(response);
    });
  }

  private getSortedByLocationName(provinceName: string): void {
    this.activityService.getByLocationName(this.page, provinceName).subscribe(response => {
      this.setPublicVariables(response);
    });
  }

  private setPublicVariables(response: any): void {
    this.activities = response.content;
    this.isFirstPage = response.first;
    this.isLastPage = response.last;
    this.totalPages = response.totalPages;
  }

  //The child component "location-selector" returns the name of the selected location or
  //the string "all" (when no location is selected). Therefore, the property "activities"
  //will be obtained filtered or not by the backend based on the returned string.
  public sortByLocation(locationName: string) {
    locationName == 'all' ? this.sortedByLocation = false : this.sortedByLocation = true;
    this.locationName = locationName;
    this.page = 0;
    this.getActivities();
  }
}
