export class Email {
    id: number = 0;
    sender!: string;
    recievers: string[] = [];
    subject!: string;
    body!: string;
    priority!: number;
    date!: Date;
    attachmentsList:string[]=[];
    constructor(s: string, r: string[], p: number, c: string, sub: string, date: Date,attach:string[]) {
        this.sender = s;
        this.recievers = r;
        this.priority = p;
        this.body = c;
        this.subject = sub;
        this.date = date;
        this.attachmentsList=attach;
    }
}
