import {BrowserModule} from '@angular/platform-browser'
import {NgModule} from '@angular/core'

import {AppComponent} from './app.component'
import {FormsModule, ReactiveFormsModule} from '@angular/forms'
import {AboutComponent} from './about/about.component'
import {HomeComponent} from './home/home.component'
import {AppRoutingModule} from './app-routing.module';
import { ErrorPageComponent } from './error-page/error-page.component';
import { ProductsComponent } from './products/products.component';
import { OrdersComponent } from './orders/orders.component';
import { OrderComponent } from './order/order.component'
import {HttpClientModule} from '@angular/common/http';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { AdminComponent } from './admin/admin.component';
import { ProfileComponent } from './profile/profile.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatSelectModule} from "@angular/material/select";
import {MatRadioModule} from "@angular/material/radio";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatSliderModule} from "@angular/material/slider";
import {MatInputModule} from "@angular/material/input";


@NgModule({
  declarations: [
    AppComponent,
    AboutComponent,
    HomeComponent,
    ErrorPageComponent,
    ProductsComponent,
    OrdersComponent,
    OrderComponent,
    LoginComponent,
    RegistrationComponent,
    AdminComponent,
    ProfileComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatSelectModule,
    MatRadioModule,
    MatDatepickerModule,
    MatSliderModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
