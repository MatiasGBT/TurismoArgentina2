import { Component, OnInit } from '@angular/core';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { Province } from 'src/app/models/province';
import { ProvinceService } from 'src/app/services/province.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-province-form',
  templateUrl: './province-form.component.html',
  styleUrls: ['./province-form.component.css']
})
export class ProvinceFormComponent implements OnInit {
  public province: Province = {} as Province;
  public image!: File;
  public imageTypeError: boolean = false;
  public imageSrc: any = '';

  constructor(private activatedRoute: ActivatedRoute, private provinceService: ProvinceService,
    private sanitizer: DomSanitizer, private router: Router) {}

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      if (params['id'] > 0) this.getProvince(params['id']);
    });
  }

  private getProvince(id: number) {
    this.provinceService.getById(id).subscribe(province => this.province = province);
  }

  selectImage(event: any) {
    let inputImage = event.target.files[0];
    if (inputImage.type.indexOf('image') < 0) {
      this.imageTypeError = true;
    } else {
      this.image = inputImage;
      this.imageSrc = this.sanitizer.bypassSecurityTrustUrl(URL.createObjectURL(this.image)) as SafeUrl;
    }
  }

  public saveProvince(): void {
    this.province.idProvince ? this.updateProvince() : this.createProvince();
  }

  public updateProvince(): void {
    this.provinceService.update(this.province).subscribe(response => {
      Swal.fire({title: response.message, timer: 1500, timerProgressBar: true, showConfirmButton: false});
      if (this.image) this.uploadPhoto();
      this.router.navigate(['/admin/provinces']);
    });
  }

  public createProvince(): void {
    /*The image is mandatory so, in order to create the province, this property must be defined.
    This is done because, in the backend, the endpoint for creating an image is separate from the
    endpoint for creating a province, so the first searches by id and the province needs to be
    created first to do that.*/
    this.province.image = "Empty for now";
    this.provinceService.create(this.province).subscribe(response => {
      Swal.fire({title: response.message, timer: 1500, timerProgressBar: true, showConfirmButton: false});
      this.province = response.province;
      if (this.image) this.uploadPhoto();
      this.router.navigate(['/admin/provinces']);
    });
  }

  private uploadPhoto(): void {
    this.provinceService.uploadPhoto(this.image, this.province.idProvince).subscribe(response => {
      console.log(response.message);
    });
  }

}
