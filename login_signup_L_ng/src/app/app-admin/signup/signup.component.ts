import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { SignupService } from './signup.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})

export class SignupComponent {
  person: FormGroup;

  strongCheck: boolean=true;
  strongCheckMessage:string = "";
  strongConfirmCheckMessage = "";

  submittionStatus:string="";


  constructor(formBuilder: FormBuilder, private service: SignupService){
    this.person = formBuilder.group({
      firstName: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(25), Validators.pattern('^[a-z]+$')]),
      lastName: new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(15), Validators.pattern('^[a-z]+$')]),
      username: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required]),
      confirmPassword: new FormControl('', [Validators.required])

    });

  }

  get firstName(){
    return this.person.get('firstName');
  }

  get lastName(){
    return this.person.get('lastName');
  }

  get username(){
    return this.person.get('username');
  }

  get password(){
    return this.person.get('password');
  }

  get confirmPassword(){
    return this.person.get('confirmPassword');
  }


  checkStrongPassword(): boolean{
    console.log("Inside check Strong ");
    var password = this.person.value.password;
    if(password.search(/[a-z]/) == -1){
      this.strongCheckMessage = "One lowercase Required";
      return false;
    }
    if(password.search(/[A-Z]/) == -1){
      this.strongCheckMessage = "One Upper Required";
      return false;
    }
    if(password.search(/[0-9]/) == -1){
      this.strongCheckMessage = "One Digit Required";
      return false;
    }
    if( password.indexOf('_') ==-1 && password.search(/\W/) == -1){
      this.strongCheckMessage = "One special Character Required";
      return false;
    }

    return true;
  }


  checkConfirmStrongPassword(): boolean{
    console.log("Inside check confirm Strong ");
    var confirmPassword = this.person.value.confirmPassword;
    var password = this.person.value.password;
    if(confirmPassword.search(/[a-z]/) == -1){
      this.strongConfirmCheckMessage = "C - Password - One lowercase Required";
      return false;
    }
    if(confirmPassword.search(/[A-Z]/) == -1){
      this.strongConfirmCheckMessage = "C - Password -One Upper Required";
      return false;
    }
    if(confirmPassword.search(/[0-9]/) == -1){
      this.strongConfirmCheckMessage = "C - Password -One Digit Required";
      return false;
    }
    if( confirmPassword.indexOf('_') ==-1 && confirmPassword.search(/\W/) == -1){
      this.strongConfirmCheckMessage = "C - Password -One special Character Required";
      return false;
    }
    console.log(this.person.value.password);
    console.log(this.confirmPassword);
    if(password != confirmPassword){
      this.strongConfirmCheckMessage = "Not matching";
      return false;
    }

    return true;
  }

  
  



  save(){

    if(this.person.invalid){
      this.person.markAllAsTouched();
      return;
    }

    this.person.removeControl('confirmPassword');

    //call to service here
    console.log('checking ='+this.person.value.firstName);
    
    this.service.save(this.person).subscribe(
      r1 => {
        console.log(r1);
        alert("Registered Successfully"+ r1);
      }
    );
    
    console.log("Saved to final ");

  }


}
