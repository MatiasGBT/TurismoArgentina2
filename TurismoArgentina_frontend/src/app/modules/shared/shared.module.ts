import { NgModule } from '@angular/core';
import { TranslateLoader, TranslateModule, TranslateStore } from '@ngx-translate/core';
import { HttpLoaderFactory } from '../../app.module';
import { HttpClient } from '@angular/common/http';

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
  exports: [TranslateModule],
  providers:[TranslateStore]
})
export class SharedModule { }
