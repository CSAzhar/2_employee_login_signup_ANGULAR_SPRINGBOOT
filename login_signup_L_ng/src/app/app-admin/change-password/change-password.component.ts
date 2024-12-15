import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { ChangePasswordService } from './change-password.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrl: './change-password.component.css'
})
export class ChangePasswordComponent {
  person:FormGroup;
  results: string = "abc";
  formSubmittionstatus: boolean = false;

  constructor(formBuilder: FormBuilder, private service:ChangePasswordService)
  {
    this.person = formBuilder.group({
      username: new FormControl(),
    password: new FormControl(),
    confirmPassword: new FormControl()
    });
  }

  onSubmit()
  {
    this.person.removeControl('confirmPassword');
    this.person.patchValue({username:sessionStorage.getItem('username')});
    this.service.changePassword(this.person).subscribe(
      data =>{
        this.results = data.result;
        this.formSubmittionstatus = true;
      }
    );
  }

}
