import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {User} from '../models/user';
import {AuthService} from '../auth.service';
import {Md5} from 'ts-md5/dist/md5';
import {AuthAdminService} from '../authAdmin.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
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
        this.http.get<User[]>('http://localhost:8080/test/getUserByEmail', {params: new HttpParams().set('email', this.email)})
            .subscribe(user => {
                if (user[0] != null) {
                    if (Md5.hashStr(this.pass) == user[0].pass) {
                        this.authSuccess = true;
                        if (user[0].id == 101) {
                            this.authAdmin.login();
                        }
                        sessionStorage.setItem('token', user[0].id.toString());
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

            });
    }

}
