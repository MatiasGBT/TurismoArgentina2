import { Province } from "./province";

export class Location {
    idLocation!: number;
    name!: string;
    description!: string;
    image!: string;
    price!: number;
    deletionDate: Date | undefined;
    province!: Province;
    peopleQuantity: number | undefined;
}
