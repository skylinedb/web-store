import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {User} from '../models/user';
import {AuthService} from '../auth.service';
import {Md5} from 'ts-md5/dist/md5';
import {AuthAdminService} from '../authAdmin.service';
import {ActivatedRoute, Router} from '@angular/router';
// @ts-ignore
import * as configuration from "src/app/config.json";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  apiUrl = configuration.apiUrl
  userUrl = configuration.apiNameUser
  findUserByEmailURL=configuration.User.findByEmail


  email = '';
  pass = '';
  user: User[] = [];
  authSuccess: boolean;
  testCredentials = '';
  private returnUrl: string;

  constructor(private http: HttpClient
    , private auth: AuthService
    , private authAdmin: AuthAdminService
    , private router: Router
    , private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';

  }

  loginUser() {
    // this.http.get<User>('http://localhost:8080/api/user/findByEmail', {params: new HttpParams().set('email', this.email)})
    this.http.get<User>(this.apiUrl + this.userUrl + this.findUserByEmailURL, {params: new HttpParams().set('email', this.email)})
      .subscribe(user => {
        this.loginService(user);
      });
  }

  testForAdmin(user: User) {
    if (user.admin_toggle == true) {
      this.authAdmin.login();
    }
  }

  loginService(user: User) {
    if (user != null) {
      if (Md5.hashStr(this.pass) == user.pass) {
        this.authSuccess = true;
        this.testForAdmin(user);
        sessionStorage.setItem('token', user.id.toString());
        sessionStorage.getItem('token');
        this.auth.login();
        this.testCredentials = 'Успешно';
        this.router.navigateByUrl(this.returnUrl);
        // window.location.replace('/');
      } else {
        this.testCredentials = 'Неправильный пароль';
      }
    } else {
      this.testCredentials = 'Нет такой почты';
    }
  }

}
