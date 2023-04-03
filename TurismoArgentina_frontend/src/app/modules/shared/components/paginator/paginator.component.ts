import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-paginator',
  templateUrl: './paginator.component.html',
  styleUrls: ['./paginator.component.css']
})
export class PaginatorComponent implements OnInit {
  @Input() page: number = 0;
  @Input() isFirstPage: boolean = true;
  @Input() isLastPage: boolean = false;
  @Input() totalPages: number = 1;
  @Output() changePageEvent = new EventEmitter<number>();

  constructor() { }

  ngOnInit(): void {
  }

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
}
