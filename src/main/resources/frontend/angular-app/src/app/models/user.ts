import {Discount} from "./discount";
import {Product} from "./product";
import {Order} from "./order";
import {Contact} from "./contact";

export class User {
  constructor(
    public first_name: string,
    public last_name: string,
    public email: string,
    public pass: string,
    public id?: number,
    public admin_toggle?: boolean,
    public orders?: Array<Order>,
    public contacts?: Array<Contact>,
    public discount_id?: Discount
  ) {

  }
}
