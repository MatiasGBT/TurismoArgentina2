import { Component, OnInit } from '@angular/core';
import { Activity } from 'src/app/models/activity';
import { Location } from 'src/app/models/location';
import { Province } from 'src/app/models/province';
import { ActivityService } from 'src/app/services/activity.service';
import { LocationService } from 'src/app/services/location.service';
import { ProvinceService } from 'src/app/services/province.service';
import { TranslateTextService } from 'src/app/services/translate-text.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'admin-provinces-nondeletedtable',
  templateUrl: './non-deleted-provinces-table.component.html',
  styleUrls: ['./non-deleted-provinces-table.component.css']
})
export class NonDeletedProvincesTableComponent implements OnInit {
  public provinces: Province[] = [];
  public page: number = 0;
  public isFirstPage: boolean = true;
  public isLastPage: boolean = false;
  public totalPages: number = 1;
  public provincesAreLoaded: boolean = false;
  private filteredByName: boolean = false;
  private provinceName: string = "";

  constructor(private provinceService: ProvinceService,
    private translateTextService: TranslateTextService,
    private locationService: LocationService,
    private activityService: ActivityService) { }

  ngOnInit(): void {
    this.getProvinces();
  }

  public changePage(pageToGo: number) {
    this.page = pageToGo;
    this.provincesAreLoaded = false;
    this.provinces = [];
    this.filteredByName ? this.getProvincesByName() : this.getProvinces();
  }

  private getProvinces() {
    this.provinceService.getAll(this.page, false).subscribe(response => {
      this.setPropertiesWithResponse(response);
    });
  }

  private getProvincesByName(): void {
    this.provinceService.getAllByName(this.page, false, this.provinceName).subscribe(response => {
      this.setPropertiesWithResponse(response);
    });
  }

  private setPropertiesWithResponse(response: any): void {
    this.provinces = response.content;
    this.isFirstPage = response.first;
    this.isLastPage = response.last;
    this.totalPages = response.totalPages;
    this.provincesAreLoaded = true;
  }

  public search(name: string): void {
    if (name.length <= 20) {
      this.provinceName = name;
      this.filteredByName = true;
    }
    this.changePage(0);
  }

  public resetSearch(): void {
    this.provinceName = "";
    this.filteredByName = false;
    this.changePage(0);
  }

  public async showDeleteModal(province: Province): Promise<void> {
    const title: string = await this.translateTextService.getTranslatedStringByUrl('ADMIN_PANEL.PROVINCES.DELETE_MODAL.TITLE');
    const text: string = await this.translateTextService.getTranslatedStringByUrl('ADMIN_PANEL.PROVINCES.DELETE_MODAL.TEXT');
    const confirmBtnText: string = await this.translateTextService.getTranslatedStringByUrl('ADMIN_PANEL.PROVINCES.DELETE_MODAL.CONFIRM_BTN');
    const cancelBtnText: string = await this.translateTextService.getTranslatedStringByUrl('ADMIN_PANEL.PROVINCES.DELETE_MODAL.CANCEL_BTN');
    Swal.fire({
      title: title, text: text, confirmButtonText: confirmBtnText, cancelButtonText: cancelBtnText,
      confirmButtonColor: '#e53170', cancelButtonColor: '#2F80ED', showCancelButton: true, icon: 'warning'
    }).then((result) => {
      if (result.isConfirmed) {
        province.deletionDate = new Date();
        this.deleteProvince(province);
      }
    });
  }

  private deleteProvince(province: Province): void {
    this.provinceService.update(province).subscribe(response => {
      this.showConfirmationModal(response.message);
      this.deleteLocationsByProvince(province);
    });
  }

  private showConfirmationModal(message: string): void {
    Swal.fire({
      title: message, showConfirmButton: false,
      timer: 1500, timerProgressBar: true
    }).then(() => window.location.reload());
  }

  private deleteLocationsByProvince(province: Province): void {
    this.locationService.getByProvinceId(province.idProvince).subscribe(locations => {
      locations.forEach(location =>  this.deleteLocation(location));
    });
  }

  private deleteLocation(location: Location): void {
    location.deletionDate = new Date();
    this.locationService.update(location).subscribe();
    this.deleteActivitiesByLocation(location);
  }

  private deleteActivitiesByLocation(location: Location): void {
    this.activityService.getByLocationId(location.idLocation).subscribe(activities => {
      activities.forEach(activity => this.deleteActivity(activity));
    });
  }

  private deleteActivity(activity: Activity): void {
    activity.deletionDate = new Date();
    this.activityService.update(activity).subscribe();
  }
}
