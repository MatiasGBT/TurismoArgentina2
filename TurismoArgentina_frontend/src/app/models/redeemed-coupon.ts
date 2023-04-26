import { Coupon } from "./coupon";
import { User } from "./user";

export interface RedeemedCoupon {
    idRedeemedCoupon: number;
    user: User
    coupon: Coupon;
    date: Date;
    isUsed: boolean;
}
