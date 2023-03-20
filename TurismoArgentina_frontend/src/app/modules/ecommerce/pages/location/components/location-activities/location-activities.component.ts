import { Component, Input, OnInit } from '@angular/core';
import { Activity } from 'src/app/models/activity';
import { ActivityService } from 'src/app/services/activity.service';

@Component({
  selector: 'ecommerce-location-activities',
  templateUrl: './location-activities.component.html',
  styleUrls: ['./location-activities.component.css']
})
export class LocationActivitiesComponent implements OnInit {
  @Input() activities: Activity[] = [];

  constructor(private activityService: ActivityService) { }

  ngOnInit(): void {
  }

  public goToActivity(idActivity: number): void {
    this.activityService.navigateToActivity(idActivity);
  }

}
