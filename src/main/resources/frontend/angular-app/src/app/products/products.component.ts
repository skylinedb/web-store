import {Component, OnInit} from '@angular/core';
import {delay} from 'rxjs/operators';
import {HttpClient} from '@angular/common/http';
import {Product} from '../models/product';
// @ts-ignore
import * as configuration from "src/app/config.json";


@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit {

  apiUrl = configuration.apiUrl
  productUrl = configuration.apiNameProduct
  findAllProductsURL = configuration.Product.findAll


  loading = false;
  products: Product[] = [];

  constructor(private http: HttpClient) {
  }

  ngOnInit() {
    this.fetchProducts();
  }

  fetchProducts() {
    this.loading = true;
    // this.http.get<Product[]>('http://localhost:8080/test/giveMeAllProducts')
    this.http.get<Product[]>(this.apiUrl + this.productUrl + this.findAllProductsURL)
      .pipe(delay(1500))
      .subscribe(todos => {
        this.products = todos;
        this.loading = false;
      });
  }
}
