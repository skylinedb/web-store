import {ActivatedRouteSnapshot, CanActivate, CanActivateChild, Router, RouterStateSnapshot} from '@angular/router'
import {Observable} from 'rxjs'
import {Injectable} from '@angular/core'
import {AuthAdminService} from './authAdmin.service'

@Injectable({providedIn: 'root'})
export class AuthAdminGuard implements CanActivate, CanActivateChild {

    constructor(
        private authService: AuthAdminService,
        private router: Router
    ) {}

    canActivate(
        route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot
    ): Observable<boolean> | Promise<boolean> | boolean {
        return this.authService.isAuthenticated().then(isAuth => {
            if (isAuth) {
                return true
            } else {
                this.router.navigate(['/login'], {
                    queryParams: {
                        returnUrl: state.url,
                        auth: false
                    }
                })
            }
        })
    }

    canActivateChild(
        route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot
    ): Observable<boolean> | Promise<boolean> | boolean  {
        return this.canActivate(route, state)
    }
}
