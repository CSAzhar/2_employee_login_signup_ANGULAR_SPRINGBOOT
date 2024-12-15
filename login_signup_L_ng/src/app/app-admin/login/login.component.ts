import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { LoginService } from './login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  person: FormGroup;
  formSubmissionStatus: boolean =  false;
  message: string = "";

  constructor(formBuilder: FormBuilder, private service: LoginService){
    this.person = formBuilder.group({
      username: new FormControl(),
      password: new FormControl()
    });

  }

  onSubmit(){

    this.service.login(this.person).subscribe( data => {
      this.message = data.message;
        if(data.loginStatus){
          this.formSubmissionStatus = true;
          sessionStorage.setItem('username', this.person.value.username);

        }else{
          this.person.patchValue({password:''});
        }
    } );

  }

}
