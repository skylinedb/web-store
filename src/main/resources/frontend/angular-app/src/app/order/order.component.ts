import {Component, OnInit} from '@angular/core';
import {Product} from '../models/product';
import {Order} from '../models/order';
import {User} from '../models/user';
import {catchError, delay} from 'rxjs/operators';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Router} from '@angular/router';
// @ts-ignore
import * as configuration from "src/app/config.json";
import {throwError} from "rxjs";


@Component({
    selector: 'app-order',
    templateUrl: './order.component.html',
    styleUrls: ['./order.component.scss']
})
export class OrderComponent implements OnInit {

    constructor(private http: HttpClient, private router: Router) {
    }

  apiUrl = configuration.apiUrl
  orderUrl = configuration.apiNameOrder
  productUrl = configuration.apiNameProduct
  userUrl = configuration.apiNameUser
  findAllProductsURL = configuration.Product.findAll
  saveOrderURL = configuration.Order.loadOrder
  findUserByIdURL=configuration.User.findById


    allProducts: Product[] = [];
    user: User;
    orderProducts: Product[] = [];
    address = '';
    email = '';
    chooseProducts: Product[] = [];

    ngOnInit() {
        this.fetchProducts();
        this.fetchUser();
    }

    fetchProducts() {
        this.http.get<Product[]>(this.apiUrl+this.productUrl+this.findAllProductsURL)
            .pipe(catchError(this.handleError))
            .subscribe(todos => {
                this.allProducts = todos;
            });
    }

    fetchUser() {
        let key = sessionStorage.getItem('token');
        this.http.get<User>(this.apiUrl+this.userUrl+this.findUserByIdURL, {params: new HttpParams().set('id', key)})
            .pipe(catchError(this.handleError))
            .subscribe(user => {
                this.user = user;
                this.email = user.email;
            });
    }


    addToOrder(i) {
        this.orderProducts.push(this.allProducts[i]);
    }

    createOrder() {
        const current = new Date();
        const newOrder: Order = {
            address: this.address,
            userId: this.user.id,
            user: this.user,
            products: this.orderProducts,
            timestamp: current
        };

        this.http.post<Order>(this.apiUrl+this.orderUrl+this.saveOrderURL, newOrder)
          .pipe(catchError(this.handleError))
            .subscribe(user => {
                console.log('Order', newOrder);
                // this.users.push(user);
                console.log(newOrder);
                this.address = '';
                this.router.navigateByUrl('orders');
            });
    }

    deleteFromOrder(i: number) {
        this.orderProducts.splice(i, 1);
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
      errorMessage = `Message: ${message}`;
    }
    window.alert(errorMessage);
    return throwError(errorMessage);
  }
}
