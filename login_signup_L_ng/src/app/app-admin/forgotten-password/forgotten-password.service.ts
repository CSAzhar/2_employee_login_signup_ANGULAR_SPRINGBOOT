import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ForgottenPasswordService {
  url: string = 'http://localhost:9090/person';

  constructor(private client:HttpClient) { }

  sendEmail(email:string){
    return this.client.get<any>(this.url+'/forgottenPassword/'+email);
  }
}
