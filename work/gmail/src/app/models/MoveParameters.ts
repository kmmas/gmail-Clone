export class MoveInfo {
    ids!: number[];
    fromFolder!: string;
    toFolder!: string;
    constructor(ids: number[], from: string, to: string) {
        this.ids = ids;
        this.fromFolder = from;
        this.toFolder = to;
    }
}