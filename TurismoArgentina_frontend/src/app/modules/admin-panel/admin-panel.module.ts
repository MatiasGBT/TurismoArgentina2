import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../shared/shared.module';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { AdminPanelRoutingModule } from './admin-panel-routing.module';
import { ProvincesComponent } from './pages/provinces/provinces.component';
import { NonDeletedProvincesTableComponent } from './pages/provinces/components/non-deleted-provinces-table/non-deleted-provinces-table.component';
import { DeletedProvincesTableComponent } from './pages/provinces/components/deleted-provinces-table/deleted-provinces-table.component';
import { LocationsComponent } from './pages/locations/locations.component';
import { NonDeletedLocationsTableComponent } from './pages/locations/components/non-deleted-locations-table/non-deleted-locations-table.component';
import { DeletedLocationsTableComponent } from './pages/locations/components/deleted-locations-table/deleted-locations-table.component';
import { ActivitiesComponent } from './pages/activities/activities.component';
import { NonDeletedActivitiesTableComponent } from './pages/activities/components/non-deleted-activities-table/non-deleted-activities-table.component';
import { DeletedActivitiesTableComponent } from './pages/activities/components/deleted-activities-table/deleted-activities-table.component';
import { SearchBarComponent } from './components/search-bar/search-bar.component';
import { ProvinceFormComponent } from './pages/provinces/pages/province-form/province-form.component';
import { LocationFormComponent } from './pages/locations/pages/location-form/location-form.component';
import { ActivityFormComponent } from './pages/activities/pages/activity-form/activity-form.component';



@NgModule({
  declarations: [
    DashboardComponent,
    ProvincesComponent,
    NonDeletedProvincesTableComponent,
    DeletedProvincesTableComponent,
    LocationsComponent,
    NonDeletedLocationsTableComponent,
    DeletedLocationsTableComponent,
    ActivitiesComponent,
    NonDeletedActivitiesTableComponent,
    DeletedActivitiesTableComponent,
    SearchBarComponent,
    ProvinceFormComponent,
    LocationFormComponent,
    ActivityFormComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    AdminPanelRoutingModule
  ]
})
export class AdminPanelModule { }
