import {Category} from '../categorys/category';
import {Account} from '../accounts/account';
import {Establishment} from '../establishments/establishment';

export class Movement {
    id: number;
    name: string;
    date: Date;
    value: number;
    interest: number;
    totalValue: number;
    portion: number;
    portionTotal: number;
    category: Category = new Category();
    account: Account = new Account();
    establishment: Establishment = new Establishment();
}
