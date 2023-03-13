import { NgModule } from '@angular/core';
import { TranslateLoader, TranslateModule, TranslateStore } from '@ngx-translate/core';
import { HttpLoaderFactory } from '../../app.module';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { SpinnerComponent } from 'src/app/components/spinner/spinner.component';

@NgModule({
  declarations: [
    SpinnerComponent
  ],
  imports: [
    TranslateModule.forChild({
      defaultLanguage: 'en',
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    }),
  ],
  exports: [
    TranslateModule,
    FormsModule,
    SpinnerComponent
  ],
  providers: [TranslateStore]
})
export class SharedModule { }
