import {Discount} from "./discount";

export class User {
  constructor(
    public first_name: string,
    public last_name: string,
    public email: string,
    public pass: string,
    public id?: number,
    public admin_toogle?: boolean,
    public discount_id?: Discount
  ) {

  }
}
