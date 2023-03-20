import { Component, OnInit } from '@angular/core';
import { Location } from 'src/app/models/location';
import { LocationService } from 'src/app/services/location.service';

@Component({
  selector: 'ecommerce-locations-locations-section',
  templateUrl: './locations-section.component.html',
  styleUrls: ['./locations-section.component.css']
})
export class LocationsPageLocationsSectionComponent implements OnInit {
  public locations: Location[] = [];
  public page: number = 0;
  public isFirstPage: boolean = true;
  public isLastPage: boolean = false;
  public totalPages: number = 1;
  private sortedByProvince: boolean = false;
  private provinceName: string = 'all';

  constructor(private locationService: LocationService) { }

  ngOnInit(): void {
    this.getLocations();
  }

  public goToLocation(idLocation: number): void {
    this.locationService.navigateToLocation(idLocation);
  }

  public changePage(pageToGo: number): void {
    this.page = pageToGo;
    this.locations = [];
    this.getLocations();
  }

  private getLocations(): void {
    if (!this.sortedByProvince) this.getAllLocations();
    else this.getSortedByProvinceName(this.provinceName);
  }

  private getAllLocations(): void {
    this.locationService.getAll(this.page).subscribe(response => {
      this.setPublicVariables(response);
    });
  }

  private getSortedByProvinceName(provinceName: string): void {
    this.locationService.getByProvinceName(this.page, provinceName).subscribe(response => {
      this.setPublicVariables(response);
    });
  }

  private setPublicVariables(response: any): void {
    this.locations = response.content;
    this.isFirstPage = response.first;
    this.isLastPage = response.last;
    this.totalPages = response.totalPages;
  }

  //The child component "province-selector" returns the name of the selected province or
  //the string "all" (when no province is selected). Therefore, the property "locations"
  //will be obtained filtered or not by the backend based on the returned string.
  public sortByProvince(provinceName: string) {
    provinceName == 'all' ? this.sortedByProvince = false : this.sortedByProvince = true;
    this.provinceName = provinceName;
    this.page = 0;
    this.getLocations();
  }
}
