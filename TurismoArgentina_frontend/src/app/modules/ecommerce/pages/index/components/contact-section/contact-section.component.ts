import { Component, OnInit } from '@angular/core';
import emailjs, { EmailJSResponseStatus } from '@emailjs/browser';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'ecommerce-index-contact-section',
  templateUrl: './contact-section.component.html',
  styleUrls: ['./contact-section.component.css']
})
export class ContactSectionComponent implements OnInit {
  public sendButtonText: string = "";

  constructor(private translate: TranslateService) { }

  ngOnInit(): void {
    this.translate.get('ECOMMERCE.INDEX.CONTACT.FORM.SEND').subscribe(response => this.sendButtonText = response);
  }

  public sendEmail(e: Event): void {
    const form = e.target as HTMLFormElement;
    e.preventDefault();
    emailjs.sendForm('service_rqhjck7', 'template_xt1mboq', form, 'Y6QgArDZ95MWp6_mr')
      .then((result: EmailJSResponseStatus) => {
        console.log(result.text);
        form.reset();
      }, (error) => {
        console.log(error.text);
      });
  }

}
