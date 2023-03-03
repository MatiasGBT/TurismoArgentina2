import { Directive, ElementRef, HostListener, Input } from '@angular/core';

@Directive({
  selector: '[parallaxDirective]'
})
export class ParallaxDirective {
  @Input('ratio') parallaxRatio: number = 1;
  private initialTop: number = 0;

  constructor(private elementRef: ElementRef) {
    this.initialTop = this.elementRef.nativeElement.getBoundingClientRect().top;
    this.initialTop += window.innerWidth < 768 ? 60 : 80;
  }

  @HostListener("window:scroll", ["$event"])
  onWindowScroll() {
    this.elementRef.nativeElement.style.top = (this.initialTop - (window.scrollY * this.parallaxRatio)) + 'px';
  }
}
