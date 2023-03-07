import { NgModule } from '@angular/core';
import { TranslateLoader, TranslateModule, TranslateStore } from '@ngx-translate/core';
import { HttpLoaderFactory } from '../../app.module';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [],
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
    FormsModule
  ],
  providers: [TranslateStore]
})
export class SharedModule { }
