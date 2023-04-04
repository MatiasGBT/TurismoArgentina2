import { Component, OnInit } from '@angular/core';
import { Activity } from 'src/app/models/activity';
import { ActivityService } from 'src/app/services/activity.service';
import { TranslateTextService } from 'src/app/services/translate-text.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'adminpage-activities-deletedtable',
  templateUrl: './deleted-activities-table.component.html',
  styleUrls: ['./deleted-activities-table.component.css']
})
export class DeletedActivitiesTableComponent implements OnInit {
  public activities: Activity[] = [];
  public page: number = 0;
  public isFirstPage: boolean = true;
  public isLastPage: boolean = false;
  public totalPages: number = 1;
  public activitiesAreLoaded: boolean = false;

  constructor(private activityService: ActivityService,
    private translateTextService: TranslateTextService) { }

  ngOnInit(): void {
    this.getActivities();
  }

  public changePage(pageToGo: number) {
    this.page = pageToGo;
    this.activities = [];
    this.getActivities();
  }

  private getActivities() {
    this.activityService.getAll(this.page, true).subscribe(response => {
      this.activities = response.content;
      this.isFirstPage = response.first;
      this.isLastPage = response.last;
      this.totalPages = response.totalPages;
      this.activitiesAreLoaded = true;
    });
  }

  public async showRestoreModal(activity: Activity): Promise<void> {
    const title: string = await this.translateTextService.getTranslatedStringByUrl('ADMIN_PANEL.ACTIVITIES.RESTORE_MODAL.TITLE');
    const text: string = await this.translateTextService.getTranslatedStringByUrl('ADMIN_PANEL.ACTIVITIES.RESTORE_MODAL.TEXT');
    const confirmBtnText: string = await this.translateTextService.getTranslatedStringByUrl('ADMIN_PANEL.ACTIVITIES.RESTORE_MODAL.CONFIRM_BTN');
    const cancelBtnText: string = await this.translateTextService.getTranslatedStringByUrl('ADMIN_PANEL.ACTIVITIES.RESTORE_MODAL.CANCEL_BTN');
    Swal.fire({
      title: title, text: text, confirmButtonText: confirmBtnText, cancelButtonText: cancelBtnText,
      confirmButtonColor: '#e53170', cancelButtonColor: '#2F80ED', showCancelButton: true, icon: 'warning'
    }).then((result) => {
      if (result.isConfirmed) {
        activity.deletionDate = null;
        this.restoreActivity(activity);
      }
    });
  }

  private restoreActivity(activity: Activity): void {
    this.activityService.modify(activity).subscribe(response => this.showConfirmationModal(response.message));
  }

  private showConfirmationModal(message: string): void {
    Swal.fire({
      title: message, showConfirmButton: false,
      timer: 1500, timerProgressBar: true
    }).then(() => window.location.reload());
  }
}