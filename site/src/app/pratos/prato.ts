import { Restaurante } from '../restaurantes/restaurante';

export class Prato {
    id: number;
    name: string;
    description: string;
    restaurant: Restaurante = new Restaurante();
}