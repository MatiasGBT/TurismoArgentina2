import { Location } from "./location";

export interface Activity {
    idActivity: number;
    name: string;
    description: string;
    image1: string;
    image2: string;
    image3: string;
    price: number;
    duration: number;
    deletionDate: Date | null;
    location: Location;
    peopleQuantity: number;
}
