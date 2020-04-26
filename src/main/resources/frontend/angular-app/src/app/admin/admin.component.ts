import {Component, OnInit} from '@angular/core';
import {Product} from '../models/product';
import {Order} from '../models/order';
import {User} from '../models/user';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Md5} from 'ts-md5/dist/md5';
import {error} from "util";

@Component({
    selector: 'app-admin',
    templateUrl: './admin.component.html',
    styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit {
    allOrders: Order[] = [];
    allUsers: User[] = [];
    allProducts: Product[] = [];
    resetActive: boolean = false;
    idOfReset: number;
    newResetPass = '';
    createProductFlag: boolean = false;
    priceOfProduct = '';
    nameOfProduct = '';

    constructor(private http: HttpClient) {
    }

    ngOnInit() {
        this.fetchUsers();
        this.fetchOrders();
        this.fetchProducts();
    }

    fetchUsers() {
        // this.http.get<User[]>('http://localhost:8080/test/giveMeAllUsers')
        this.http.get<User[]>('http://localhost:8080/api/user/findAll')
            .subscribe(users => {
                this.allUsers = users;
            });
    }

    fetchProducts() {
      // this.http.get<Product[]>('http://localhost:8080/test/giveMeAllProducts')
        this.http.get<Product[]>('http://localhost:8080/api/product/findAll')
            .subscribe(products => {
                this.allProducts = products;
            });
    }

    fetchOrders() {
        new Date().getTimezoneOffset();
      // this.http.get<Order[]>('http://localhost:8080/test//giveMeAllOrders')
        this.http.get<Order[]>('http://localhost:8080/api/order/findAll')
            .subscribe(orders => {
                orders.sort((a, b) => <any> new Date(b.timestamp) - <any> new Date(a.timestamp));
                this.allOrders = orders;
            });
    }

    deleteProduct(i: number) {
        let deleteProduct = this.allProducts[i];
        // this.http.post<Product>('http://localhost:8080/test/deleteProduct', deleteProduct)
        this.http.post<Product>('http://localhost:8080/api/product/delete', deleteProduct)
            .subscribe(delProduct => {
                this.allProducts.splice(i, 1);
            });
    }

    deleteOrder(i: number) {
        let deleteOrder = this.allOrders[i];
        // this.http.post<Order>('http://localhost:8080/test/deleteOrder', deleteOrder)
        this.http.post<Order>('http://localhost:8080/api/order/delete', deleteOrder)
            .subscribe(delOrder => {
                this.allOrders.splice(i, 1);
            });
    }

    deleteUser(i: number) {
        let deleteUser = this.allUsers[i];
        // this.http.post<User>('http://localhost:8080/test/deleteUser', deleteUser)
        this.http.post<User>('http://localhost:8080/api/user/delete', deleteUser)
            .subscribe(delUser => {
                this.allUsers.splice(i, 1);
            });
    }

    resetPassword(i: number) {
        let resetPasswordUser = this.allUsers[i];
        let newPassword: any = Md5.hashStr(this.newResetPass);
        resetPasswordUser.pass = newPassword;
        // this.http.post<User>('http://localhost:8080/test/updateUser', resetPasswordUser)
        this.http.post<User>('http://localhost:8080/api/user/update', resetPasswordUser)
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

        // this.http.post<User>('http://localhost:8080/test/saveProduct', newProduct)
        this.http.post<User>('http://localhost:8080/api/product/load', newProduct)
            .subscribe(user => {
                console.log('Product', newProduct);
                this.nameOfProduct = '';
                this.priceOfProduct = '';
                this.allProducts.push(newProduct);
            });
    }

    activateCreateProduct(i: number) {
        this.createProductFlag = true;
    }
}
