import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ForgottenPasswordService } from './forgotten-password.service';

@Component({
  selector: 'app-forgotten-password',
  templateUrl: './forgotten-password.component.html',
  styleUrl: './forgotten-password.component.css'
})
export class ForgottenPasswordComponent {

  form:FormGroup;
  results: string = "";
  status: boolean = true;

  constructor(formBuilder: FormBuilder, private service: ForgottenPasswordService){
    this.form = formBuilder.group({
      email: new FormControl('', [Validators.required, Validators.email])
    });
  }

  get email(){
    return this.form.get('email');
  }

  onSubmit(){
    alert(1);
    if(this.form.invalid){
      this.form.markAllAsTouched();
    }
    alert(2);

    console.log('printing form email '+this.form.value.email);

    this.service.sendEmail(this.form.value.email).subscribe(
      data => {
        this.status = false;
        this.results = data.results;
        console.log(this.results);

      }
    )
  }

}
