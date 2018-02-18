import {Location} from '../addresses/location';

export class Establishment {
    id: number;
    name: string;
    location: Location = new Location();
}