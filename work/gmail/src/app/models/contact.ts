export class contact {
    name!: string;
    addresses!: string[];
    constructor(name: string, addresses: string[]) {
        this.name = name;
        this.addresses = addresses;
    }
}