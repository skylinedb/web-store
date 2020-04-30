import {Injectable} from '@angular/core'

@Injectable({providedIn: 'root'})
export class AuthAdminService {
    public isAuth = false

    login() {
        this.isAuth = true
    }

    logout() {
        this.isAuth = false;
        sessionStorage.removeItem('token');
        window.location.replace('http://localhost:4200/');
    }

    isAuthenticated(): Promise<boolean> {
        return new Promise(resolve => {
            setTimeout(() => {
                resolve(this.isAuth)
            }, 1000)
        })
    }
}
