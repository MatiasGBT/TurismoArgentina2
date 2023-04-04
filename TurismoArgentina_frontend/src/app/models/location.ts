import { Province } from "./province";

export interface Location {
    idLocation: number;
    name: string;
    description: string;
    image: string;
    price: number;
    deletionDate: Date | null;
    province: Province;
    peopleQuantity: number;
}
