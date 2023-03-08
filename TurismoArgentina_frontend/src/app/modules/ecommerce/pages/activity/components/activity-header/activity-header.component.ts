import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'ecommerce-activity-header',
  templateUrl: './activity-header.component.html',
  styleUrls: ['./activity-header.component.css']
})
export class ActivityHeaderComponent implements OnInit {
  @Input() activity: any = {};

  constructor() { }

  ngOnInit(): void {
  }

}
