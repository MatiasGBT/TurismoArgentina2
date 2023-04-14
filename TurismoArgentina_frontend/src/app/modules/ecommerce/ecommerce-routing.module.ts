import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IndexComponent } from './pages/index/index.component';
import { ProvincesComponent } from './pages/provinces/provinces.component';
import { LocationsComponent } from './pages/locations/locations.component';
import { ActivitiesComponent } from './pages/activities/activities.component';
import { ProvinceComponent } from './pages/province/province.component';
import { LocationComponent } from './pages/location/location.component';
import { ActivityComponent } from './pages/activity/activity.component';
import { CartComponent } from './pages/cart/cart.component';
import { AuthGuard } from 'src/app/guards/auth.guard';
import { AccountComponent } from './pages/account/account.component';

const routes: Routes = [
  {path: '', redirectTo: 'index', pathMatch: 'full'},
  {path: 'index', component: IndexComponent},
  {path: 'provinces', component: ProvincesComponent},
  {path: 'provinces/:id', component: ProvinceComponent},
  {path: 'locations', component: LocationsComponent},
  {path: 'locations/:id', component: LocationComponent},
  {path: 'activities', component: ActivitiesComponent},
  {path: 'activities/:id', component: ActivityComponent},
  {path: 'cart', component: CartComponent},
  {path: 'account', component: AccountComponent},
  {path: 'login', component: AccountComponent, canActivate: [AuthGuard], data: {role: 'user'}},
]

@NgModule({
  declarations: [],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ECommerceRoutingModule { }