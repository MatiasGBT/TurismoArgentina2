import { Component, OnInit } from '@angular/core';
import { Activity } from 'src/app/models/activity';
import { Location } from 'src/app/models/location';
import { ActivityService } from 'src/app/services/activity.service';
import { LocationService } from 'src/app/services/location.service';
import { TranslateTextService } from 'src/app/services/translate-text.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'admin-locations-nondeletedtable',
  templateUrl: './non-deleted-locations-table.component.html',
  styleUrls: ['./non-deleted-locations-table.component.css']
})
export class NonDeletedLocationsTableComponent  implements OnInit {
  public locations: Location[] = [];
  public page: number = 0;
  public isFirstPage: boolean = true;
  public isLastPage: boolean = false;
  public totalPages: number = 1;
  public locationsAreLoaded: boolean = false;
  private filteredByName: boolean = false;
  private locationName: string = "";

  constructor(private locationService: LocationService,
    private translateTextService: TranslateTextService,
    private activityService: ActivityService) { }

  ngOnInit(): void {
    this.getLocations();
  }

  public changePage(pageToGo: number) {
    this.page = pageToGo;
    this.locationsAreLoaded = false;
    this.locations = [];
    this.filteredByName ? this.getLocationsByName() : this.getLocations();
  }

  private getLocations() {
    this.locationService.getAll(this.page, false).subscribe(response => {
      this.setPropertiesWithResponse(response);
    });
  }

  private getLocationsByName() {
    this.locationService.getAllByName(this.page, false, this.locationName).subscribe(response => {
      this.setPropertiesWithResponse(response);
    });
  }

  private setPropertiesWithResponse(response: any): void {
    this.locations = response.content;
    this.isFirstPage = response.first;
    this.isLastPage = response.last;
    this.totalPages = response.totalPages;
    this.locationsAreLoaded = true;
  }

  public search(name: string): void {
    if (name.length <= 45) {
      this.locationName = name;
      this.filteredByName = true;
    }
    this.changePage(0);
  }

  public resetSearch(): void {
    this.locationName = "";
    this.filteredByName = false;
    this.changePage(0);
  }

  public async showDeleteModal(location: Location): Promise<void> {
    const title: string = await this.translateTextService.getTranslatedStringByUrl('ADMIN_PANEL.LOCATIONS.DELETE_MODAL.TITLE');
    const text: string = await this.translateTextService.getTranslatedStringByUrl('ADMIN_PANEL.LOCATIONS.DELETE_MODAL.TEXT');
    const confirmBtnText: string = await this.translateTextService.getTranslatedStringByUrl('ADMIN_PANEL.LOCATIONS.DELETE_MODAL.CONFIRM_BTN');
    const cancelBtnText: string = await this.translateTextService.getTranslatedStringByUrl('ADMIN_PANEL.LOCATIONS.DELETE_MODAL.CANCEL_BTN');
    Swal.fire({
      title: title, text: text, confirmButtonText: confirmBtnText, cancelButtonText: cancelBtnText,
      confirmButtonColor: '#e53170', cancelButtonColor: '#2F80ED', showCancelButton: true, icon: 'warning'
    }).then((result) => {
      if (result.isConfirmed) {
        location.deletionDate = new Date();
        this.deleteLocation(location);
      }
    });
  }

  private deleteLocation(location: Location): void {
    this.locationService.update(location).subscribe(response => {
      this.showConfirmationModal(response.message);
      this.deleteActivitiesByLocation(location);
    });
  }

  private showConfirmationModal(message: string): void {
    Swal.fire({
      title: message, showConfirmButton: false,
      timer: 1500, timerProgressBar: true
    }).then(() => window.location.reload());
  }

  private deleteActivitiesByLocation(location: Location): void {
    this.activityService.getByLocationId(location.idLocation).subscribe(activities => {
      activities.forEach(activity =>  this.deleteActivity(activity));
    });
  }

  private deleteActivity(activity: Activity): void {
    activity.deletionDate = new Date();
    this.activityService.update(activity).subscribe();
  }
}
