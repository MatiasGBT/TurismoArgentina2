import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'adminpanel-searchbar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.css']
})
export class SearchBarComponent {
  public name: string = "";
  @Output() searchEvent = new EventEmitter<string>();
  @Output() resetEvent = new EventEmitter<void>();

  public detectEnter(event: any): void {
    if (event.key === 'Enter' || event.keyCode == 13) this.search();
  }

  public reset(): void {
    this.resetEvent.emit();
  }

  public search(): void {
    if (this.name.length > 0) this.searchEvent.emit(this.name);
    else this.resetEvent.emit();
  }
}
