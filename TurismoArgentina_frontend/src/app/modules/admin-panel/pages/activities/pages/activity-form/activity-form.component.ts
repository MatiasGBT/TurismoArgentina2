import { Component, OnInit } from '@angular/core';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { Activity } from 'src/app/models/activity';
import { ActivityService } from 'src/app/services/activity.service';
import { LocationService } from 'src/app/services/location.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-activity-form',
  templateUrl: './activity-form.component.html',
  styleUrls: ['./activity-form.component.css']
})
export class ActivityFormComponent implements OnInit {
  public activity: Activity = {} as Activity;
  public image1!: File;
  public image2!: File;
  public image3!: File;
  public image1TypeError: boolean = false;
  public image2TypeError: boolean = false;
  public image3TypeError: boolean = false;
  public image1Src: any = '';
  public image2Src: any = '';
  public image3Src: any = '';
  public locationNames: string[] = [];
  public locationSelected: string = "";

  constructor(private activatedRoute: ActivatedRoute, private activityService: ActivityService,
    private sanitizer: DomSanitizer, private router: Router,
    private locationService: LocationService) {}

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      if (params['id'] > 0) this.getActivity(params['id']);
    });
    this.getLocationNames();
  }

  private getActivity(id: number) {
    this.activityService.getById(id).subscribe(activity => {
      this.activity = activity;
      this.locationSelected = this.activity.location.name;
    });
  }

  public selectImage(event: any, imageNumber: number): void {
    let inputImage = event.target.files[0];
    if (inputImage.type.indexOf('image') < 0) this.setImageTypeErrorWithNumber(imageNumber);
    else this.setImageWithNumber(inputImage, imageNumber);
  }

  private setImageTypeErrorWithNumber(imageNumber: number): void {
    if (imageNumber == 1) {
      this.image1TypeError = true;
    } else if (imageNumber == 2) {
      this.image2TypeError = true;
    } else if (imageNumber == 3) {
      this.image3TypeError = true;
    }
  }

  private setImageWithNumber(inputImage: any, imageNumber: number): void {
    if (imageNumber == 1) {
      this.image1 = inputImage;
      this.image1Src = this.sanitizer.bypassSecurityTrustUrl(URL.createObjectURL(this.image1)) as SafeUrl;
      this.image1TypeError = false;
    } else if (imageNumber == 2) {
      this.image2 = inputImage;
      this.image2Src = this.sanitizer.bypassSecurityTrustUrl(URL.createObjectURL(this.image2)) as SafeUrl;
      this.image2TypeError = false;
    } else if (imageNumber == 3) {
      this.image3 = inputImage;
      this.image3Src = this.sanitizer.bypassSecurityTrustUrl(URL.createObjectURL(this.image3)) as SafeUrl;
      this.image3TypeError = false;
    }
  }

  public saveActivity(): void {
    this.activity.idActivity ? this.updateActivity() : this.createActivity();
  }

  public updateActivity(): void {
    this.activityService.update(this.activity).subscribe(response => {
      Swal.fire({title: response.message, timer: 1500, timerProgressBar: true, showConfirmButton: false});
      if (this.image1) this.uploadPhoto(1);
      if (this.image2) this.uploadPhoto(2);
      if (this.image3) this.uploadPhoto(3);
      this.router.navigate(['/admin/activities']);
    });
  }

  public createActivity(): void {
    /*The image is mandatory so, in order to create the province, this property must be defined.
    This is done because, in the backend, the endpoint for creating an image is separate from the
    endpoint for creating a province, so the first searches by id and the province needs to be
    created first to do that.*/
    this.activity.image1 = "Empty for now";
    this.activityService.create(this.activity).subscribe(response => {
      Swal.fire({title: response.message, timer: 1500, timerProgressBar: true, showConfirmButton: false});
      this.activity = response.activity;
      if (this.image1) this.uploadPhoto(1);
      if (this.image2) this.uploadPhoto(2);
      if (this.image3) this.uploadPhoto(3);
      this.router.navigate(['/admin/activities']);
    });
  }

  private uploadPhoto(imageNumber: number): void {
    if (imageNumber == 1) {
      this.activityService.uploadPhoto(this.image1, this.activity.idActivity, imageNumber).subscribe(response => {
        console.log(response.message);
      });
    } else if (imageNumber == 2) {
      this.activityService.uploadPhoto(this.image2, this.activity.idActivity, imageNumber).subscribe(response => {
        console.log(response.message);
      });
    } else if (imageNumber == 3) {
      this.activityService.uploadPhoto(this.image3, this.activity.idActivity, imageNumber).subscribe(response => {
        console.log(response.message);
      });
    }
  }

  private getLocationNames() {
    this.locationService.getAllLocationNames().subscribe(locationNames => this.locationNames = locationNames);
  }

  public selectLocation(): void {
    this.locationService.getByName(this.locationSelected).subscribe(location => {
      this.activity.location = location;
    });
  }
}
