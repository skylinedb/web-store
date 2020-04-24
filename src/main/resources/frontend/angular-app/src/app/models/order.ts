import {Product} from './product';
import {User} from './user';


export class Order {
    constructor(
        public address: string,
        public user_id?: number,
        public user?: User,
        public id?: number,
        public products?: Array<Product>,
        public timestamp?: Date
    ) {
    }
}
