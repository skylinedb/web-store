import {NgModule} from '@angular/core'
import {RouterModule, Routes} from '@angular/router'
import {HomeComponent} from './home/home.component'
import {AboutComponent} from './about/about.component'
import {ErrorPageComponent} from './error-page/error-page.component'
import {AuthGuard} from './auth.guard'
import {ProductsComponent} from './products/products.component';
import {RegistrationComponent} from './registration/registration.component';
import {LoginComponent} from './login/login.component';
import {OrdersComponent} from './orders/orders.component';
import {OrderComponent} from './order/order.component';
import {AdminComponent} from './admin/admin.component';
import {AuthAdminGuard} from './authAdmin.guard';


const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'about', component: AboutComponent},
  {path: 'products' , component: ProductsComponent, canActivate: [AuthGuard]},
  {path: 'registration' , component: RegistrationComponent},
  {path: 'orders' , component: OrdersComponent, canActivate: [AuthGuard]},
  {path: 'order' , component: OrderComponent, canActivate: [AuthGuard]},
  {path: 'admin' , component: AdminComponent, canActivate: [AuthAdminGuard]},
  {path: 'login' , component: LoginComponent},
  {path: 'error', component: ErrorPageComponent},
  {path: '**', redirectTo: '/error'}
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
