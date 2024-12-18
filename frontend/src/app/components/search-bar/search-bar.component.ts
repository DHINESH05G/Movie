import { Component, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.css']
})
export class SearchBarComponent {
  @Output() searchEvent = new EventEmitter<string>();
  searchTerm: string = '';

  onSearch(): void {
    this.searchEvent.emit(this.searchTerm);
  }
}
