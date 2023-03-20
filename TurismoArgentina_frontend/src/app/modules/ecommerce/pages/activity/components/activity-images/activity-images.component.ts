import { Component, Input, OnInit } from '@angular/core';
import { Activity } from 'src/app/models/activity';

@Component({
  selector: 'ecommerce-activity-images',
  templateUrl: './activity-images.component.html',
  styleUrls: ['./activity-images.component.css']
})
export class ActivityImagesComponent implements OnInit {
  @Input() activity!: Activity;

  constructor() { }

  ngOnInit(): void {
  }

}
