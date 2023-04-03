import { Component, ElementRef, HostListener, OnChanges, OnInit, Renderer2, ViewChild } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  private isMenuOpen: boolean = false;
  @ViewChild("navbarList") navbarList!: ElementRef;
  @ViewChild("menuButton") menuButton!: ElementRef;
  public userIsLoggedIn: boolean = false;
  public userIsAdmin: boolean = false;
  public userIsOnAdminModule: boolean = false;

  constructor(private renderer2: Renderer2, private authService: AuthService,
    private router: Router) { }

  ngOnInit(): void {
    this.authService.userLoggedInEvent.subscribe(() => {
      this.userIsLoggedIn = true;
      this.userIsAdmin = this.authService.hasRole('admin');
    });

    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        if (this.router.url.includes('shop')) this.userIsOnAdminModule = false;
        if (this.router.url.includes('admin')) this.userIsOnAdminModule = true;
      }
    });
  }

  public toggleMenu(): void {
    const screenWidth = window.innerWidth;
    if (screenWidth < 1038) {
      this.isMenuOpen = !this.isMenuOpen;
      this.isMenuOpen ? this.openMenu() : this.closeMenu();
    }
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

  @HostListener('window:resize', ['$event'])
  onResize(event: any) {
    const screenWidth = window.innerWidth;
    if (screenWidth >= 1038) {
      this.isMenuOpen = false;
      this.closeMenu();
    }
  }
}
