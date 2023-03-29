import { Injectable } from '@angular/core';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class CatchErrorService {

  constructor() { }

  public showError(ex: any): void {
    Swal.fire({title: ex.error.message, text: ex.error.error, timer: 3000, timerProgressBar: true, showConfirmButton: false});
  }
}
