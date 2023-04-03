import { NgModule } from '@angular/core';
import { TranslateModule, TranslateStore } from '@ngx-translate/core';
import { FormsModule } from '@angular/forms';
import { SpinnerComponent } from './components/spinner/spinner.component';
import { PaginatorComponent } from './components/paginator/paginator.component';

@NgModule({
  declarations: [
    SpinnerComponent,
    PaginatorComponent
  ],
  imports: [
    TranslateModule
  ],
  exports: [
    TranslateModule,
    FormsModule,
    SpinnerComponent,
    PaginatorComponent
  ],
  providers: [TranslateStore]
})
export class SharedModule { }
