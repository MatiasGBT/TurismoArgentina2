import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'ecommerce-activity-images',
  templateUrl: './activity-images.component.html',
  styleUrls: ['./activity-images.component.css']
})
export class ActivityImagesComponent implements OnInit {
  @Input() activity: any = {};

  constructor() { }

  ngOnInit(): void {
  }

}
