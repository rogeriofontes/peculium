import {RestaurantType} from '../restaurants-types/restaurant-type';

export class Restaurante {
    id: number;
    name: string;
    description: string;
    address: string;
    rate: number;
    restaurantType: RestaurantType = new RestaurantType();
}