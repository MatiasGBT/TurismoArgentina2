import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Activity } from 'src/app/models/activity';
import { Location } from 'src/app/models/location';
import { ActivityService } from 'src/app/services/activity.service';
import { LocationService } from 'src/app/services/location.service';

@Component({
  selector: 'app-location',
  templateUrl: './location.component.html',
  styleUrls: ['./location.component.css']
})
export class LocationComponent implements OnInit {
  public location: Location | undefined;
  public activities: Activity[] = [];

  constructor(private activatedRoute: ActivatedRoute, private locationService: LocationService,
    private activityService: ActivityService) { }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      if (params['id']) {
        this.getLocation(params['id']);
        this.getActivities(params['id']);
      }
    });
  }

  private getLocation(idLocation: number): void {
    this.locationService.getById(idLocation).subscribe(location => this.location = location);
  }

  private getActivities(idLocation: number): void {
    this.activityService.getByLocationId(idLocation).subscribe(activities => this.activities = activities);
  }
}
