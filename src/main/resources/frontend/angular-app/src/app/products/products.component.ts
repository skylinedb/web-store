import {Component, OnInit} from '@angular/core';
import {delay} from 'rxjs/operators';
import {HttpClient} from '@angular/common/http';
import {Product} from '../models/product';



@Component({
    selector: 'app-products',
    templateUrl: './products.component.html',
    styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit {

    loading = false;
    products: Product[] = [];

    constructor(private http: HttpClient) {
    }

    ngOnInit() {
      this.fetchProducts();
    }

    fetchProducts() {
        this.loading = true;
        this.http.get<Product[]>('http://localhost:8080/test/giveMeAllProducts')
            .pipe(delay(1500))
            .subscribe(todos => {
                this.products = todos;
                this.loading = false;
            });
    }
}
