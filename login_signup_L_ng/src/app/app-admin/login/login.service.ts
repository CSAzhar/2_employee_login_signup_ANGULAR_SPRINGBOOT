import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  url: string = "http://localhost:9090/person";

  constructor(private client: HttpClient) { }

  login(person: FormGroup){
    return this.client.post<any>(this.url + '/login', person.value);
  }
}
