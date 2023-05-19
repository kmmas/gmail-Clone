export class DeleteInfo {
    ids!: number[];
    fromFolder!: string;
    constructor(ids: number[], from: string) {
        this.ids = ids;
        this.fromFolder = from;
    }
}