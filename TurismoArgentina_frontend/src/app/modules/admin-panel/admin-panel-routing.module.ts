import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { AuthGuard } from 'src/app/guards/auth.guard';
import { ProvincesComponent } from './pages/provinces/provinces.component';
import { LocationsComponent } from './pages/locations/locations.component';
import { ActivitiesComponent } from './pages/activities/activities.component';
import { ProvinceFormComponent } from './pages/provinces/pages/province-form/province-form.component';
import { LocationFormComponent } from './pages/locations/pages/location-form/location-form.component';
import { ActivityFormComponent } from './pages/activities/pages/activity-form/activity-form.component';

const routes: Routes = [
  {path: '', redirectTo: 'dashboard', pathMatch: 'full'},
  {path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard], data: {role: 'admin'}},
  {path: 'provinces', component: ProvincesComponent, canActivate: [AuthGuard], data: {role: 'admin'}},
  {path: 'provinces/:id', component: ProvinceFormComponent, canActivate: [AuthGuard], data: {role: 'admin'}},
  {path: 'provinces/add', component: ProvinceFormComponent, canActivate: [AuthGuard], data: {role: 'admin'}},
  {path: 'locations', component: LocationsComponent, canActivate: [AuthGuard], data: {role: 'admin'}},
  {path: 'locations/:id', component: LocationFormComponent, canActivate: [AuthGuard], data: {role: 'admin'}},
  {path: 'locations/add', component: LocationFormComponent, canActivate: [AuthGuard], data: {role: 'admin'}},
  {path: 'activities', component: ActivitiesComponent, canActivate: [AuthGuard], data: {role: 'admin'}},
  {path: 'activities/:id', component: ActivityFormComponent, canActivate: [AuthGuard], data: {role: 'admin'}},
  {path: 'activities/add', component: ActivityFormComponent, canActivate: [AuthGuard], data: {role: 'admin'}},
]

@NgModule({
  declarations: [],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminPanelRoutingModule { }