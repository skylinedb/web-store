import {Component, OnInit} from '@angular/core';
import {environment} from "../../environments/environment";
import {Order} from "../models/order";
import {Product} from "../orders/orders.component";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Contact} from "../models/contact";
import {User} from "../models/user";
// @ts-ignore
import * as configuration from "../config.json";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  apiUrl = configuration.apiUrl
  orderUrl = configuration.apiNameOrder
  contactUrl = configuration.apiNameContact
  deleteOrderURL = configuration.Order.deleteOrder
  findOrderByUserId_withUser = configuration.Order.findByUserId_withUser
  findContactByUserId = configuration.Contact.findByUserId


  loading = false;
  contacts: Contact [] = [];
  user: User;
  orders: Order[] = [];
  products: Product [] = [];
  contactToggle: boolean = false;
  ordersToggle: boolean = false;
  createContactFlag: boolean = false;

  constructor(private http: HttpClient) {
  }

  ngOnInit() {
    this.getOrder();
    this.getUser()
  }


  getOrder() {
    new Date().getTimezoneOffset();
    let key = sessionStorage.getItem('token');
    // this.http.get<Order[]>('http://localhost:8080/test/FindOrderByUserId', {params: new HttpParams().set('id', key)})
    this.http.get<Order[]>(this.apiUrl + this.orderUrl + this.findOrderByUserId_withUser, {params: new HttpParams().set('id', key)})
      .subscribe(orders => {
        orders.sort((a, b) => <any>new Date(b.timestamp) - <any>new Date(a.timestamp));
        this.orders = orders;
      });
  }

  getUser() {
    let key = sessionStorage.getItem('token');
    // this.http.get<Order[]>('http://localhost:8080/test/FindOrderByUserId', {params: new HttpParams().set('id', key)})
    this.http.get<Contact[]>(this.apiUrl + this.contactUrl + this.findContactByUserId, {params: new HttpParams().set('id', key)})
      .subscribe(contacts => {
        // orders.sort((a, b) => <any> new Date(b.timestamp) - <any> new Date(a.timestamp));
        this.contacts = contacts;
      });
  }

  deleteOrder(i: number) {
    let deleteOrder = this.orders[i];
    // this.http.post<User>('http://localhost:8080/test/deleteOrder', deleteOrder)
    this.http.post<Order>(this.apiUrl + this.orderUrl + this.deleteOrderURL, deleteOrder)
      .subscribe(delOrder => {
        this.orders.splice(i, 1);
      });
  }

  activateCreateContact() {
    this.createContactFlag = true;
  }

  deleteContact(j: number) {

  }
}
