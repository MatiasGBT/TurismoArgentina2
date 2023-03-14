import { Province } from "./province";

export class Location {
    idLocation: number | undefined;
    name: string | undefined;
    description: string | undefined;
    image: string | undefined;
    price: number | undefined;
    deletionDate: Date | undefined;
    province: Province | undefined;
}
