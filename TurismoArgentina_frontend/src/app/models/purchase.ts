import { Activity } from "./activity";
import { Location } from "./location";
import { User } from "./user";

export interface Purchase {
    idPurchase: number;
    date: Date;
    user: User;
    locations: Location[];
    activities: Activity[];
    price: number;
    isRefunded: boolean;
}
