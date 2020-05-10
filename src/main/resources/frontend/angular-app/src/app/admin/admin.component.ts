import {Component, OnInit} from '@angular/core';
import {Product} from '../models/product';
import {Order} from '../models/order';
import {User} from '../models/user';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Md5} from 'ts-md5/dist/md5';
import {error} from "util";
// @ts-ignore
import * as configuration from "src/app/config.json";
import {Contact} from "../models/contact";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ContactType} from "../models/contactType";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit {
  apiUrl = configuration.apiUrl
  orderUrl = configuration.apiNameOrder
  productUrl = configuration.apiNameProduct
  userUrl = configuration.apiNameUser
  contactUrl = configuration.apiNameContact
  findAllUsersURL = configuration.User.findAll
  findAllProductsURL = configuration.Product.findAll
  findAllOrdersWithUserURL = configuration.Order.findAll_withUser
  deleteProductURL = configuration.Product.deleteProduct
  deleteUserURL = configuration.User.deleteUser
  deleteOrderURL = configuration.Order.deleteOrder
  updateUserURL = configuration.User.deleteUser
  saveProductURL = configuration.Product.loadProduct
  findContactByUserIdURL = configuration.Contact.findByUserId;
  deleteContactUrl = configuration.Contact.deleteContact;
  findAllContactTypes = configuration.Contact.findAllTypes;
  saveContactURL = configuration.Contact.loadContact;


  allOrders: Order[] = [];
  allUsers: User[] = [];
  allProducts: Product[] = [];
  contacts: Contact[] = [];
  resetActive: boolean = false;
  idOfReset: number;
  newResetPass = '';
  createProductFlag: boolean = false;
  priceOfProduct = '';
  nameOfProduct = '';
  contactActive: boolean = false;
  nameOfUserWhichContacts = '';
  contactForm: FormGroup;
  contactChoose: ContactType;
  contactTypes: ContactType[] = [];
  typeOfContact = '';
  errorMessage = '';
  valueOfContact = '';
  userForm: FormGroup;
  userChoose: User;






  constructor(private http: HttpClient, private formBuilder: FormBuilder) {

  }

  ngOnInit() {
    this.fetchUsers();
    this.fetchOrders();
    this.fetchProducts()
    this.userForm=this.formBuilder.group(
      {user: [null]}
    );
    this.contactForm = this.formBuilder.group(
      {contactType: [null]}
    );

  }

  fetchUsers() {
    this.http.get<User[]>(this.apiUrl + this.userUrl + this.findAllUsersURL)
      .subscribe(users => {
        this.allUsers = users;
      });
  }

  fetchProducts() {
    this.http.get<Product[]>(this.apiUrl + this.productUrl + this.findAllProductsURL)
      .subscribe(products => {
        this.allProducts = products;
      });
  }

  fetchOrders() {
    new Date().getTimezoneOffset();
    this.http.get<Order[]>(this.apiUrl + this.orderUrl + this.findAllOrdersWithUserURL)
      .subscribe(orders => {
        orders.sort((a, b) => <any>new Date(b.timestamp) - <any>new Date(a.timestamp));
        this.allOrders = orders;
      });
  }

  deleteProduct(i: number) {
    let deleteProduct = this.allProducts[i];
    this.http.post<Product>(this.apiUrl + this.productUrl + this.deleteProductURL, deleteProduct)
      .subscribe(delProduct => {
        this.allProducts.splice(i, 1);
      });
  }

  deleteOrder(i: number) {
    let deleteOrder = this.allOrders[i];
    this.http.post<Order>(this.apiUrl + this.orderUrl + this.deleteOrderURL, deleteOrder)
      .subscribe(delOrder => {
        this.allOrders.splice(i, 1);
      });
  }

  deleteUser(i: number) {
    let deleteUser = this.allUsers[i];
    this.http.post<User>(this.apiUrl + this.userUrl + this.deleteUserURL, deleteUser)
      .subscribe(delUser => {
        this.allUsers.splice(i, 1);
      });
  }

  resetPassword(i: number) {
    let resetPasswordUser = this.allUsers[i];
    let newPassword: any = Md5.hashStr(this.newResetPass);
    resetPasswordUser.pass = newPassword;
    this.http.post<User>(this.apiUrl + this.userUrl + this.updateUserURL, resetPasswordUser)
      .subscribe(delUser => {
        console.log(resetPasswordUser);
      });
    this.resetActive = false;
  }

  activateReset(i: number) {
    this.resetActive = true;
    this.idOfReset = i;
  }

  createProduct() {
    const newProduct: Product = {
      product_name: this.nameOfProduct,
      product_price: this.priceOfProduct,
    };

    this.http.post<Product>(this.apiUrl + this.productUrl + this.saveProductURL, newProduct)
      .subscribe(product => {
        console.log('Product', newProduct);
        this.nameOfProduct = '';
        this.priceOfProduct = '';
        this.allProducts.push(newProduct);
      });
  }

  activateCreateProduct() {
    this.createProductFlag = true;
  }

  activateContacts(i: number) {
    this.getContactTypes();
    this.nameOfUserWhichContacts = this.allUsers[i].first_name +' '+ this.allUsers[i].last_name;
    this.contactActive = true;

    let key = this.allUsers[i].id;
    sessionStorage.setItem('idForNewContact', key.toString());

    this.http.get<Contact[]>(this.apiUrl + this.contactUrl + this.findContactByUserIdURL, {params: new HttpParams().set('id', String(key))})
      .subscribe(contacts => {
        console.log(contacts);
        this.contacts = contacts;
      });
  }

  getContactTypes() {
    this.http.get<ContactType[]>(this.apiUrl + this.contactUrl + this.findAllContactTypes)
      .subscribe(contacts => {
        this.contactTypes = contacts;
      });
  }

  deleteContact(l: number) {
    let deleteContact = this.contacts[l];
    this.http.post<Contact>(this.apiUrl + this.contactUrl + this.deleteContactUrl, deleteContact)
      .subscribe(deleteContact => {
        this.contacts.splice(l, 1);
      });
  }

  setContactType() {
    this.typeOfContact = this.contactChoose.type;
  }

  createContact() {
    console.log(this.userChoose);
    let id =     sessionStorage.getItem('idForNewContact');;
    if (this.typeOfContact == '') {
      this.errorMessage = 'Выберите тип и нажмите кнопку "выбрать" или введите свой тип контакта';
    } else {



      const newContact: Contact = {
        type_label: this.typeOfContact,
        value: this.valueOfContact,
        userId: id
      };

      this.http.post<Contact>(this.apiUrl + this.contactUrl + this.saveContactURL, newContact)
        .subscribe(newContact => {
          this.typeOfContact = '';
          this.valueOfContact = '';
          console.log(newContact);
        });
    }
  }
}
