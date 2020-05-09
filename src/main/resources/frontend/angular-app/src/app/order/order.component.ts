import {Component, OnInit} from '@angular/core';
import {Product} from '../models/product';
import {Order} from '../models/order';
import {User} from '../models/user';
import {delay} from 'rxjs/operators';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Router} from '@angular/router';
// @ts-ignore
import * as configuration from "src/app/config.json";


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
        // this.loading = true;
        // this.http.get<Product[]>('http://localhost:8080/test/giveMeAllProducts')
        this.http.get<Product[]>(this.apiUrl+this.productUrl+this.findAllProductsURL)
            .pipe(delay(1500))
            .subscribe(todos => {
                this.allProducts = todos;
                // this.loading = false;
            });
    }

    fetchUser() {
        let key = sessionStorage.getItem('token');
        // this.http.get<User>('http://localhost:8080/test/getUserById', {params: new HttpParams().set('id', key)})
        this.http.get<User>(this.apiUrl+this.userUrl+this.findUserByIdURL, {params: new HttpParams().set('id', key)})
            .pipe(delay(1500))
            .subscribe(user => {
                this.user = user;
                this.email = user.email;
                console.log('Это User', this.user);
            });
    }


    addToOrder(i) {
        console.log(this.allProducts[i].id);
        console.log(this.allProducts[i]);
        this.orderProducts.push(this.allProducts[i]);
        // console.log('Это уже в списке на добавление',this.orderProducts[0]);
        // console.log('Это уже в списке на добавление',this.orderProducts[1]);
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

        // this.http.post<User>('http://localhost:8080/test/saveOrder', newOrder)
        this.http.post<Order>(this.apiUrl+this.orderUrl+this.saveOrderURL, newOrder)
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
}
