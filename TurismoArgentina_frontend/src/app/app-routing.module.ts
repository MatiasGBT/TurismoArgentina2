import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {path: '', redirectTo: 'shop', pathMatch: 'full'},
  {path: 'shop', loadChildren: () => import('./modules/ecommerce/ecommerce.module').then( m => m.ECommerceModule )}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
