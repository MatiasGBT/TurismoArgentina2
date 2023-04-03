import { Injectable } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Injectable({
  providedIn: 'root'
})
export class TranslateTextService {

  constructor(private translate: TranslateService) { }

  public async getTranslatedStringByUrl(url: string): Promise<string> {
    return new Promise<string>((resolve) => {
      this.translate.get(url).subscribe((responseText) => resolve(responseText));
    });
  }
}
