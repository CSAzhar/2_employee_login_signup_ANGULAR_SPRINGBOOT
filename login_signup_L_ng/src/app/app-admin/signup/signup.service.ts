import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class SignupService {

  url:string = 'http://localhost:9090/person/';

  constructor(private client:HttpClient) { }

  save(person: FormGroup){
    return this.client.post(this.url + 'save', person.value);
  }
}
