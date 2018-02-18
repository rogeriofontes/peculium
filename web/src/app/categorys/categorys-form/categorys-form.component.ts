import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';

import { CategorysService } from '../categorys.service';
import { Category } from '../category';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-categorys-form',
  templateUrl: './categorys-form.component.html',
  styleUrls: ['./categorys-form.component.scss']
})
export class CategorysFormComponent implements OnInit {
  public myForm: FormGroup; // our model driven form
  public categorys: Category[] = [];
  
  category: Category = new Category();
  title: string;

  constructor(
    private categorysService: CategorysService, 
    private router: Router, 
    private route: ActivatedRoute,
    private formBuilder: FormBuilder
  ) { 
    this.myForm = this.formBuilder.group({
      name: '',
    });
  }

  ngOnInit() {
    var id = this.route.params.subscribe(params => {
      var id = params['id'];

      this.title = id ? 'Edit User' : 'New User';

      if (!id)
        return;

      this.categorysService.getById(id)
        .then(
          category => this.category = category,
          response => {
            if (response.status == 404) {
              this.router.navigate(['NotFound']);
            }
          });
    });
  }

  onSubmit(): void {
    console.log(this.category);
    if (this.category.id){
        this.categorysService.update(this.category)
          .then(category => {
            this.categorys = null;
            this.router.navigate(['category']);
        });
    } else {
        this.categorysService.post(this.category)
          .then(restaurantType => {
            this.categorys = null;
            this.router.navigate(['category']);
        });
    }
   
  }

}
