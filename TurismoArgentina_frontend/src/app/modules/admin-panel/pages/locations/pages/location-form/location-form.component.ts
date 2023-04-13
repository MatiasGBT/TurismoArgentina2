import { Component, OnInit } from '@angular/core';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from 'src/app/models/location';
import { LocationService } from 'src/app/services/location.service';
import { ProvinceService } from 'src/app/services/province.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-location-form',
  templateUrl: './location-form.component.html',
  styleUrls: ['./location-form.component.css']
})
export class LocationFormComponent implements OnInit {
  public location: Location = {} as Location;
  public image!: File;
  public imageTypeError: boolean = false;
  public imageSrc: any = '';
  public provinceNames: string[] = [];
  public provinceSelected: string = "";

  constructor(private activatedRoute: ActivatedRoute, private locationService: LocationService,
    private sanitizer: DomSanitizer, private router: Router,
    private provinceService: ProvinceService) {}

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      if (params['id'] > 0) this.getLocation(params['id']);
    });
    this.getProvinceNames();
  }

  private getLocation(id: number) {
    this.locationService.getById(id).subscribe(location => {
      this.location = location;
      this.provinceSelected = this.location.province.name;
    });
  }

  public selectImage(event: any) {
    let inputImage = event.target.files[0];
    if (inputImage.type.indexOf('image') < 0) {
      this.imageTypeError = true;
    } else {
      this.image = inputImage;
      this.imageSrc = this.sanitizer.bypassSecurityTrustUrl(URL.createObjectURL(this.image)) as SafeUrl;
    }
  }

  public saveLocation(): void {
    this.location.idLocation ? this.updateLocation() : this.createLocation();
  }

  public updateLocation(): void {
    this.locationService.update(this.location).subscribe(response => {
      Swal.fire({title: response.message, timer: 1500, timerProgressBar: true, showConfirmButton: false});
      if (this.image) this.uploadPhoto();
      this.router.navigate(['/admin/locations']);
    });
  }

  public createLocation(): void {
    /*The image is mandatory so, in order to create the province, this property must be defined.
    This is done because, in the backend, the endpoint for creating an image is separate from the
    endpoint for creating a province, so the first searches by id and the province needs to be
    created first to do that.*/
    this.location.image = "Empty for now";
    this.locationService.create(this.location).subscribe(response => {
      Swal.fire({title: response.message, timer: 1500, timerProgressBar: true, showConfirmButton: false});
      this.location = response.location;
      if (this.image) this.uploadPhoto();
      this.router.navigate(['/admin/locations']);
    });
  }

  private uploadPhoto(): void {
    this.locationService.uploadPhoto(this.image, this.location.idLocation).subscribe(response => {
      console.log(response.message);
    });
  }

  private getProvinceNames() {
    this.provinceService.getAllProvinceNames().subscribe(provinceNames => this.provinceNames = provinceNames);
  }

  public selectProvince(): void {
    this.provinceService.getByName(this.provinceSelected).subscribe(province => {
      this.location.province = province;
    });
  }
}
