import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IndexComponent } from './pages/index/index.component';
import { SharedModule } from '../shared/shared.module';
import { ECommerceRoutingModule } from './ecommerce-routing.module';
import { IndexHeaderComponent } from './pages/index/components/index-header/index-header.component';
import { LocationsSectionComponent } from './pages/index/components/locations-section/locations-section.component';
import { ActivitiesSectionComponent } from './pages/index/components/activities-section/activities-section.component';
import { ContactSectionComponent } from './pages/index/components/contact-section/contact-section.component';
import { ProvincesComponent } from './pages/provinces/provinces.component';
import { ParallaxDirective } from './directives/parallax.directive';
import { ProvincesHeaderComponent } from './pages/provinces/components/provinces-header/provinces-header.component';
import { ProvincesSectionComponent } from './pages/provinces/components/provinces-section/provinces-section.component';
import { ProvinceSelectorComponent } from './components/province-selector/province-selector.component';
import { PaginatorComponent } from './components/paginator/paginator.component';
import { LocationsComponent } from './pages/locations/locations.component';
import { LocationsHeaderComponent } from './pages/locations/components/locations-header/locations-header.component';
import { LocationsPageLocationsSectionComponent } from './pages/locations/components/locations-section/locations-section.component';
import { ActivitiesComponent } from './pages/activities/activities.component';
import { ActivitiesHeaderComponent } from './pages/activities/components/activities-header/activities-header.component';
import { ActivitiesPageSectionComponent } from './pages/activities/components/activities-section/activities-section.component';
import { LocationSelectorComponent } from './components/location-selector/location-selector.component';
import { ProvinceComponent } from './pages/province/province.component';
import { ProvinceHeaderComponent } from './pages/province/components/province-header/province-header.component';
import { ProvinceDataComponent } from './pages/province/components/province-data/province-data.component';
import { ProvinceLocationsComponent } from './pages/province/components/province-locations/province-locations.component';

@NgModule({
  declarations: [
    IndexComponent,
    IndexHeaderComponent,
    LocationsSectionComponent,
    ActivitiesSectionComponent,
    ContactSectionComponent,
    ProvincesComponent,
    ParallaxDirective,
    ProvincesHeaderComponent,
    ProvincesSectionComponent,
    ProvinceSelectorComponent,
    PaginatorComponent,
    LocationsComponent,
    LocationsHeaderComponent,
    LocationsPageLocationsSectionComponent,
    ActivitiesComponent,
    ActivitiesHeaderComponent,
    ActivitiesPageSectionComponent,
    LocationSelectorComponent,
    ProvinceComponent,
    ProvinceHeaderComponent,
    ProvinceDataComponent,
    ProvinceLocationsComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    ECommerceRoutingModule
  ]
})
export class ECommerceModule { }
