import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class ChangePasswordService {

  url: string = "http://localhost:9090/person";
  constructor(private client:HttpClient) { }

  changePassword(person: FormGroup)
  {
    return this.client.post<any>(this.url+'/changePassword', person.value);
  }
}
