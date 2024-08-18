export class SaveSingleEntityDao{
    category:number;
    subcategory:number;
    kpi:number;
    time_line:number;

    constructor(
        category:number,
        subcategory:number,
        kpi:number,
        time_line:number
    ){
        this.category = category;
        this.subcategory = subcategory;
        this.kpi = kpi;
        this.time_line = time_line;
    }
}