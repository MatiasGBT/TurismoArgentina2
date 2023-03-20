import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Activity } from 'src/app/models/activity';
import { ActivityService } from 'src/app/services/activity.service';

@Component({
  selector: 'app-activity',
  templateUrl: './activity.component.html',
  styleUrls: ['./activity.component.css']
})
export class ActivityComponent implements OnInit {
  public activity: Activity | undefined;

  constructor(private activatedRoute: ActivatedRoute, private activityService: ActivityService) { }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      if (params['id']) {
        this.getActivity(params['id']);
      }
    });
  }

  private getActivity(idActivity: number): void {
    this.activityService.getById(idActivity).subscribe(activity => {
      console.log(activity)
      this.activity = activity
    });
  }

}
