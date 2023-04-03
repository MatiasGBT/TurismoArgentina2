import { Component, OnInit } from '@angular/core';
import { Province } from 'src/app/models/province';
import { ProvinceService } from 'src/app/services/province.service';
import { TranslateTextService } from 'src/app/services/translate-text.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'adminpage-provinces-nondeletedtable',
  templateUrl: './non-deleted-provinces-table.component.html',
  styleUrls: ['./non-deleted-provinces-table.component.css']
})
export class NonDeletedProvincesTableComponent implements OnInit {
  public provinces: Province[] = [];
  public page: number = 0;
  public isFirstPage: boolean = true;
  public isLastPage: boolean = false;
  public totalPages: number = 1;
  public provincesAreLoaded: boolean = false;

  constructor(private provinceService: ProvinceService,
    private translateTextService: TranslateTextService) { }

  ngOnInit(): void {
    this.getProvinces();
  }

  public changePage(pageToGo: number) {
    this.page = pageToGo;
    this.provinces = [];
    this.getProvinces();
  }

  private getProvinces() {
    this.provinceService.getAll(this.page, false).subscribe(response => {
      this.provinces = response.content;
      this.isFirstPage = response.first;
      this.isLastPage = response.last;
      this.totalPages = response.totalPages;
      this.provincesAreLoaded = true;
    });
  }

  public async showDeleteModal(province: Province): Promise<void> {
    const title: string = await this.translateTextService.getTranslatedStringByUrl('ADMIN_PANEL.PROVINCES.DELETE_MODAL.TITLE');
    const text: string = await this.translateTextService.getTranslatedStringByUrl('ADMIN_PANEL.PROVINCES.DELETE_MODAL.TEXT');
    const confirmBtnText: string = await this.translateTextService.getTranslatedStringByUrl('ADMIN_PANEL.PROVINCES.DELETE_MODAL.CONFIRM_BTN');
    const cancelBtnText: string = await this.translateTextService.getTranslatedStringByUrl('ADMIN_PANEL.PROVINCES.DELETE_MODAL.CANCEL_BTN');
    Swal.fire({
      title: title, text: text, confirmButtonText: confirmBtnText, cancelButtonText: cancelBtnText,
      confirmButtonColor: '#e53170', cancelButtonColor: '#2F80ED', showCancelButton: true, icon: 'warning'
    }).then((result) => {
      if (result.isConfirmed) {
        province.deletionDate = new Date();
        this.deleteProvince(province);
      }
    });
  }

  private deleteProvince(province: Province): void {
    this.provinceService.modify(province).subscribe(response => this.showConfirmationModal(response.message));
  }

  private showConfirmationModal(message: string): void {
    Swal.fire({
      title: message, showConfirmButton: false,
      timer: 1500, timerProgressBar: true
    }).then(() => window.location.reload());
  }
}
