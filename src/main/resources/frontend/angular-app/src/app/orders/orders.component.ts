import {Component, OnInit} from '@angular/core';
import {User} from '../models/user';
import {HttpClient, HttpParams} from '@angular/common/http';
import {AppComponent} from '../app.component';
import {Order} from '../models/order';
import {Product} from '../models/product';
// @ts-ignore
import * as configuration from "src/app/config.json";

// export interface Product {
//     product_name: string
//     product_price: string
//     id?: number
// }

@Component({
    selector: 'app-orders',
    templateUrl: './orders.component.html',
    styleUrls: ['./orders.component.scss']
})
export class OrdersComponent implements OnInit {
  apiUrl = configuration.apiUrl
  orderUrl = configuration.apiNameOrder
  deleteOrderURL = configuration.Order.deleteOrder
  findOrderByUserId_withUser = configuration.Order.findByUserId_withUser


  // asdas=this.apiUrl+
    loading = false;
    orders: Order[] = [];
    products: Product[] = [];

    constructor(private http: HttpClient) {
    }

    ngOnInit() {
        this.getOrder();

    }

    getOrder() {
        new Date().getTimezoneOffset();
        let key = sessionStorage.getItem('token');
        // this.http.get<Order[]>('http://localhost:8080/test/FindOrderByUserId', {params: new HttpParams().set('id', key)})
        this.http.get<Order[]>(this.apiUrl+this.orderUrl+this.findOrderByUserId_withUser, {params: new HttpParams().set('id', key)})
            .subscribe(orders => {
                orders.sort((a, b) => <any> new Date(b.timestamp) - <any> new Date(a.timestamp));
                this.orders = orders;
            });
    }

    deleteOrder(i: number) {
        let deleteOrder = this.orders[i];
        // this.http.post<User>('http://localhost:8080/test/deleteOrder', deleteOrder)
        this.http.post<Order>(this.apiUrl+this.orderUrl+this.deleteOrderURL, deleteOrder)
            .subscribe(delOrder => {
                this.orders.splice(i, 1);
            });
    }
}
