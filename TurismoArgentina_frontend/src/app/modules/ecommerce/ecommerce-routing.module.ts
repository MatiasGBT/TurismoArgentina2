import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IndexComponent } from './pages/index/index.component';
import { ProvincesComponent } from './pages/provinces/provinces.component';
import { LocationsComponent } from './pages/locations/locations.component';
import { ActivitiesComponent } from './pages/activities/activities.component';
import { ProvinceComponent } from './pages/province/province.component';

const routes: Routes = [
  {path: '', redirectTo: 'index', pathMatch: 'full'},
  {path: 'index', component: IndexComponent},
  {path: 'provinces', component: ProvincesComponent},
  {path: 'locations', component: LocationsComponent},
  {path: 'activities', component: ActivitiesComponent},
  {path: 'provinces/:id', component: ProvinceComponent},
]

@NgModule({
  declarations: [],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ECommerceRoutingModule { }