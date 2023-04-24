import { Component, ElementRef, EventEmitter, Input, Output, ViewChild } from '@angular/core';

@Component({
  selector: 'app-paginator',
  templateUrl: './paginator.component.html',
  styleUrls: ['./paginator.component.css']
})
export class PaginatorComponent {
  @Input() page: number = 0;
  @Input() isFirstPage: boolean = true;
  @Input() isLastPage: boolean = false;
  @Input() totalPages: number = 1;
  @Output() changePageEvent = new EventEmitter<number>();
  @ViewChild("inputPage") inputPage: ElementRef | undefined;

  public goFirstPage(): void {
    this.changePageEvent.emit(0);
  }

  public goPreviousPage(): void {
    this.changePageEvent.emit(this.page - 1);
  }

  public goNextPage(): void {
    this.changePageEvent.emit(this.page + 1);
  }

  public goLastPage(): void {
    this.changePageEvent.emit(this.totalPages - 1);
  }

  public goToSelectedPage(event: any): void {
    if ((event.key === 'Enter' || event.keyCode == 13) && this.inputPage) {
      let pageToGo = this.inputPage.nativeElement.value -1;
      if (pageToGo < 0) pageToGo = 0;
      if (pageToGo > this.totalPages - 1) pageToGo = this.totalPages - 1;
      this.inputPage.nativeElement.value = pageToGo + 1;
      this.changePageEvent.emit(pageToGo);
    }
  }
}
