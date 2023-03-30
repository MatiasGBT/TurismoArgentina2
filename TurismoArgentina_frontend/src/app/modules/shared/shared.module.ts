import { NgModule } from '@angular/core';
import { TranslateModule, TranslateStore } from '@ngx-translate/core';
import { FormsModule } from '@angular/forms';
import { SpinnerComponent } from 'src/app/components/spinner/spinner.component';

@NgModule({
  declarations: [
    SpinnerComponent
  ],
  imports: [
    TranslateModule
  ],
  exports: [
    TranslateModule,
    FormsModule,
    SpinnerComponent
  ],
  providers: [TranslateStore]
})
export class SharedModule { }
