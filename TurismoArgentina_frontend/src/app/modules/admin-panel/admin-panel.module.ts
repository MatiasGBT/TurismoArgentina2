import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../shared/shared.module';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { AdminPanelRoutingModule } from './admin-panel-routing.module';
import { ProvincesComponent } from './pages/provinces/provinces.component';
import { NonDeletedProvincesTableComponent } from './pages/provinces/components/non-deleted-provinces-table/non-deleted-provinces-table.component';
import { DeletedProvincesTableComponent } from './pages/provinces/components/deleted-provinces-table/deleted-provinces-table.component';



@NgModule({
  declarations: [
    DashboardComponent,
    ProvincesComponent,
    NonDeletedProvincesTableComponent,
    DeletedProvincesTableComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    AdminPanelRoutingModule
  ]
})
export class AdminPanelModule { }
