export class SPORTI{
    id:number;
    sportId:number;
    repN:number;
    setN:number;
    registration:Date;

    constructor(
        id:number,
        sportId:number,
        repN:number,
        setN:number,
        registration:Date,
    ){
        this.id=id;
        this.sportId=sportId;
        this.repN=repN;
        this.setN=setN;
        this.registration=registration;
    }
}