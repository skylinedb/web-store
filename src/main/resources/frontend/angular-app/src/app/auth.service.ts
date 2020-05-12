import {Injectable} from '@angular/core'

@Injectable({providedIn: 'root'})
export class AuthService {
  public isAuth = false

  login() {
    this.isAuth = true
  }

    logout() {
        this.isAuth = false;
        sessionStorage.removeItem('token');
        window.location.replace('/');
    }

  isAuthenticated(): Promise<boolean> {
    return new Promise(resolve => {
      setTimeout(() => {
        resolve(this.isAuth)
      }, 1000)
    })
  }
}
