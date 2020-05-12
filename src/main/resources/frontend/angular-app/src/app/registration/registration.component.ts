import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Md5} from 'ts-md5/dist/md5';
import * as configuration from "src/app/config.json";
import {User} from "../models/user";
import {throwError} from "rxjs";
import {catchError} from "rxjs/operators";



@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {

  apiUrl = configuration.apiUrl
  userUrl = configuration.apiNameUser
  saveUserURL = configuration.User.loadUser


  firstname = '';
  lastname = '';
  email = '';
  pass = '';
  testCredentials = '';

  constructor(private http: HttpClient) {
  }

  ngOnInit() {
  }

  saveUser() {
    let newPassword: any = Md5.hashStr(this.pass);
    const newUser: User = {
      first_name: this.firstname,
      last_name: this.lastname,
      email: this.email,
      pass: newPassword
    };

    // if (this.firstname == '') {
    //   this.testCredentials = 'Введите Имя!';
    // } else if (this.lastname == '') {
    //   this.testCredentials = 'Введите Фамилию!';
    // } else if (this.email == '') {
    //   this.testCredentials = 'Введите почту!';
    // } else if (this.pass == '') {
    //   this.testCredentials = 'Введите пароль!';
    // } else {
      // this.http.post<User>('http://localhost:8080/api/user/save', newUser)
      this.http.post<User>(this.apiUrl + this.userUrl + this.saveUserURL, newUser).pipe(catchError(this.handleError))
        .subscribe(user => {
          console.log('User', newUser);
          this.firstname = '';
          this.lastname = '';
          this.email = '';
          this.pass = '';
          this.testCredentials = 'Успешно!'
        });
    // }
  }

  handleError(error) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // client-side error
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // server-side error
      let allString = error.error.message;
      let message=allString.match(/messageTemplate=.*'/gm);
      errorMessage = `Error Code: ${error.status}\nMessage: ${message}`;
    }
    window.alert(errorMessage);
    return throwError(errorMessage);
  }

}
