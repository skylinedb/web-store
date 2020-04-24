import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Md5} from 'ts-md5/dist/md5';

export interface User {
    first_name: string
    last_name: string
    email: string
    pass: string
    id?: number
}


@Component({
    selector: 'app-registration',
    templateUrl: './registration.component.html',
    styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {

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

        if (this.firstname == '') {
            this.testCredentials = 'Введите Имя!';
        } else if (this.lastname == '') {
            this.testCredentials = 'Введите Фамилию!';
        } else if (this.email == '') {
            this.testCredentials = 'Введите почту!';
        } else if (this.pass == '') {
            this.testCredentials = 'Введите пароль!';
        } else {
            this.http.post<User>('http://localhost:8080/test/saveUser', newUser)
                .subscribe(user => {
                    console.log('User', newUser);
                    this.firstname = '';
                    this.lastname = '';
                    this.email = '';
                    this.pass = '';
                    this.testCredentials = 'Успешно!'
                });
        }
    }

}
