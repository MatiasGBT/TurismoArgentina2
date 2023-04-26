import { Component, OnInit } from '@angular/core';
import { Province } from 'src/app/models/province';
import { ProvinceService } from 'src/app/services/province.service';
import { TranslateTextService } from 'src/app/services/translate-text.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'admin-provinces-deletedtable',
  templateUrl: './deleted-provinces-table.component.html',
  styleUrls: ['./deleted-provinces-table.component.css']
})
export class DeletedProvincesTableComponent implements OnInit {
  public provinces: Province[] = [];
  public page: number = 0;
  public isFirstPage: boolean = true;
  public isLastPage: boolean = false;
  public totalPages: number = 1;
  public provincesAreLoaded: boolean = false;
  private filteredByName: boolean = false;
  private provinceName: string = "";

  constructor(private provinceService: ProvinceService,
    private translateTextService: TranslateTextService) { }

  ngOnInit(): void {
    this.getProvinces();
  }

  public changePage(pageToGo: number) {
    this.page = pageToGo;
    this.provincesAreLoaded = false;
    this.provinces = [];
    this.filteredByName ? this.getProvincesByName() : this.getProvinces();
  }

  private getProvinces() {
    this.provinceService.getAll(this.page, true).subscribe(response => {
      this.setPropertiesWithResponse(response);
    });
  }

  private getProvincesByName(): void {
    this.provinceService.getAllByName(this.page, true, this.provinceName).subscribe(response => {
      this.setPropertiesWithResponse(response);
    });
  }

  private setPropertiesWithResponse(response: any): void {
    this.provinces = response.content;
    this.isFirstPage = response.first;
    this.isLastPage = response.last;
    this.totalPages = response.totalPages;
    this.provincesAreLoaded = true;
  }

  public search(name: string): void {
    if (name.length <= 20) {
      this.provinceName = name;
      this.filteredByName = true;
    }
    this.changePage(0);
  }

  public resetSearch(): void {
    this.provinceName = "";
    this.filteredByName = false;
    this.changePage(0);
  }

  public async showRestoreModal(province: Province): Promise<void> {
    const title: string = await this.translateTextService.getTranslatedStringByUrl('ADMIN_PANEL.PROVINCES.RESTORE_MODAL.TITLE');
    const text: string = await this.translateTextService.getTranslatedStringByUrl('ADMIN_PANEL.PROVINCES.RESTORE_MODAL.TEXT');
    const confirmBtnText: string = await this.translateTextService.getTranslatedStringByUrl('ADMIN_PANEL.PROVINCES.RESTORE_MODAL.CONFIRM_BTN');
    const cancelBtnText: string = await this.translateTextService.getTranslatedStringByUrl('ADMIN_PANEL.PROVINCES.RESTORE_MODAL.CANCEL_BTN');
    Swal.fire({
      title: title, text: text, confirmButtonText: confirmBtnText, cancelButtonText: cancelBtnText,
      confirmButtonColor: '#e53170', cancelButtonColor: '#2F80ED', showCancelButton: true, icon: 'warning'
    }).then((result) => {
      if (result.isConfirmed) {
        province.deletionDate = null;
        this.restoreProvince(province);
      }
    });
  }

  private restoreProvince(province: Province): void {
    this.provinceService.update(province).subscribe(response => this.showConfirmationModal(response.message));
  }

  private showConfirmationModal(message: string): void {
    Swal.fire({
      title: message, showConfirmButton: false,
      timer: 1500, timerProgressBar: true
    }).then(() => window.location.reload());
  }
}
