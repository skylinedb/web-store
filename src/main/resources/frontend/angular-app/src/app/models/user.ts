export class User {
    constructor(
        public first_name: string,
        public last_name: string,
        public email: string,
        public pass: string,
        public id?: number,
    ) {
    }
}
