import { Component, ElementRef, OnInit, Renderer2, ViewChild } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  private isMenuOpen: boolean = false;
  @ViewChild("navbarList") navbarList!: ElementRef;
  @ViewChild("menuButton") menuButton!: ElementRef;

  constructor(private renderer2: Renderer2) { }

  ngOnInit(): void {
  }

  public activateMenu(): void {
    this.isMenuOpen = !this.isMenuOpen;
    this.isMenuOpen ? this.openMenu() : this.closeMenu();
  }

  private openMenu(): void {
    this.renderer2.addClass(this.navbarList.nativeElement, "menu-opened");
    this.renderer2.removeClass(this.menuButton.nativeElement, "fa-bars");
    this.renderer2.addClass(this.menuButton.nativeElement, "fa-xmark");
  }

  private closeMenu(): void {
    this.renderer2.removeClass(this.navbarList.nativeElement, "menu-opened");
    this.renderer2.removeClass(this.menuButton.nativeElement, "fa-xmark");
    this.renderer2.addClass(this.menuButton.nativeElement, "fa-bars");
  }
}
