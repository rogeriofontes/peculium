import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { CategorysService } from './categorys.service';
import { Category } from './category';

@Component({
  selector: 'app-categorys',
  templateUrl: './categorys.component.html',
  styleUrls: ['./categorys.component.scss']
})
export class CategorysComponent implements OnInit {
  public categorys: Category[] = [];
  public category: Category;

  constructor( 
    private categoryService: CategorysService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit() {
    this.categorys = [];
    this.get();
  }

  get(): void {
    this.categorys = null; 
    this.categoryService
      .get()
      .then(result => {
          this.categorys = result
      });
  }

  getById(id): void {
    this.categoryService
      .getById(id)
      .then(result => {
          this.category = result
      });
  }

  getByName(name): void {
    console.log('->--'+name);
    this.categoryService
      .getByName(name)
      .then(result => {
          this.category = result
          this.categorys = [];
          this.categorys.push(this.category);
          console.log(this.categorys);
      });
  }

   delete(category){
    if (confirm("Are you sure you want to delete " + category.name + "?")) {
      var index = this.categorys.indexOf(category);
      this.categoryService.delete(category.id)
         .then(result => {
           this.get();
            //this.restaurantTypes.push(restaurantType);
            //window.location.reload();
           // this.router.navigate(['restaurants-types?refresh=1']);
          });
    }
  }

}
