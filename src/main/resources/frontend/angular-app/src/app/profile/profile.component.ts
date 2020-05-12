import {Component, OnInit} from '@angular/core';
import {environment} from "../../environments/environment";
import {Order} from "../models/order";
import {Product} from "../models/product";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Contact} from "../models/contact";
import {User} from "../models/user";
// @ts-ignore
import * as configuration from "../config.json";
import {ContactType} from "../models/contactType";
import {FormBuilder, FormGroup} from "@angular/forms";
import {throwError} from "rxjs";
import {catchError} from "rxjs/operators";

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
  findAllContactTypes = configuration.Contact.findAllTypes
  saveContactURL = configuration.Contact.loadContact
  deleteContactUrl = configuration.Contact.deleteContact;


  loading = false;
  contacts: Contact [] = [];
  contactTypes: ContactType[] = []
  user: User;
  orders: Order[] = [];
  products: Product [] = [];
  contactToggle: boolean = false;
  ordersToggle: boolean = true;
  createContactFlag: boolean = false;
  typeOfContact = '';
  valueOfContact = '';
  contactForm: FormGroup;
  contactChoose: ContactType;
  // otherTypeActivate: boolean = false;
  form: FormGroup;
  errorMessage = '';


  constructor(private http: HttpClient, private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.getOrder();
    this.getUser()
    this.getContactTypes()
    this.contactForm = this.formBuilder.group(
      {contactType: [null]}
    );

  }


  getOrder() {
    new Date().getTimezoneOffset();
    let key = sessionStorage.getItem('token');
    // this.http.get<Order[]>('http://localhost:8080/test/FindOrderByUserId', {params: new HttpParams().set('id', key)})
    this.http.get<Order[]>(this.apiUrl + this.orderUrl + this.findOrderByUserId_withUser, {params: new HttpParams().set('id', key)})
      .pipe(catchError(this.handleError))
      .subscribe(orders => {
        orders.sort((a, b) => <any>new Date(b.timestamp) - <any>new Date(a.timestamp));
        this.orders = orders;
      });
  }

  getUser() {
    let key = sessionStorage.getItem('token');
    // this.http.get<Order[]>('http://localhost:8080/test/FindOrderByUserId', {params: new HttpParams().set('id', key)})
    this.http.get<Contact[]>(this.apiUrl + this.contactUrl + this.findContactByUserId, {params: new HttpParams().set('id', key)})
      .pipe(catchError(this.handleError))
      .subscribe(contacts => {
        // orders.sort((a, b) => <any> new Date(b.timestamp) - <any> new Date(a.timestamp));
        this.contacts = contacts;
      });
  }

  deleteOrder(i: number) {
    let deleteOrder = this.orders[i];
    // this.http.post<User>('http://localhost:8080/test/deleteOrder', deleteOrder)
    this.http.post<Order>(this.apiUrl + this.orderUrl + this.deleteOrderURL, deleteOrder)
      .pipe(catchError(this.handleError))
      .subscribe(delOrder => {
        this.orders.splice(i, 1);
      });
  }

  getContactTypes() {
    let key = sessionStorage.getItem('token');
    this.http.get<ContactType[]>(this.apiUrl + this.contactUrl + this.findAllContactTypes)
      .pipe(catchError(this.handleError))
      .subscribe(contacts => {
        this.contactTypes = contacts;
      });
  }


  deleteContact(j: number) {
    let deleteContact = this.contacts[j];
    // this.http.post<User>('http://localhost:8080/test/deleteOrder', deleteOrder)
    this.http.post<Contact>(this.apiUrl + this.contactUrl + this.deleteContactUrl, deleteContact)
      .pipe(catchError(this.handleError))
      .subscribe(delContact => {
        this.contacts.splice(j, 1);
      });
  }

  createContact() {
    let id = sessionStorage.getItem('token');
    if (this.typeOfContact == '') {
      this.errorMessage = 'Выберите тип и нажмите кнопку "выбрать" или введите свой тип контакта';
    } else {




    const newContact: Contact = {
      type_label: this.typeOfContact,
      value: this.valueOfContact,
      userId: id
    };

    this.http.post<Contact>(this.apiUrl + this.contactUrl + this.saveContactURL, newContact)
      .pipe(catchError(this.handleError))
      .subscribe(newContact => {
        this.typeOfContact = '';
        this.valueOfContact = '';
        console.log(newContact);
        // this.orders.splice(i, 1);
      });
    }
  }


  setContactType() {
    this.typeOfContact = this.contactChoose.type;
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
      errorMessage = `Error Code: ${error.status}\nMessage: ${message}`;
    }
    window.alert(errorMessage);
    return throwError(errorMessage);
  }
}
