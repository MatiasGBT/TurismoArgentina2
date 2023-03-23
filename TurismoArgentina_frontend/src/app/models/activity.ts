import { Location } from "./location";

export class Activity {
    idActivity!: number;
    name!: string;
    description!: string;
    image1!: string;
    image2: string | undefined;
    image3: string | undefined;
    price!: number;
    duration: number | undefined;
    deletionDate: Date | undefined;
    location!: Location;
    peopleQuantity: number | undefined;
}
