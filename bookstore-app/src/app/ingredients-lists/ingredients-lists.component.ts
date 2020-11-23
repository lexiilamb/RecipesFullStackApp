import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { IngredientsListEntity } from '../models/ingredients-lists';
import { RecipeService } from '../recipe-service.service';

@Component({
  selector: 'app-ingredients-lists',
  templateUrl: './ingredients-lists.component.html',
  styleUrls: ['./ingredients-lists.component.scss']
})
export class IngredientsListsComponent implements OnInit {

  constructor(private recipeService: RecipeService, private formBuilder: FormBuilder) { }

  addForm: FormGroup;
  tableData: IngredientsListEntity[];
  saveResponse: String = ''

  ngOnInit() {
    this.recipeService.getIngredientsLists().subscribe(data => {
      this.tableData = data;
    });

    this.addForm = this.formBuilder.group({
      recipe_id: ['', Validators.required],
      ingredient: ['', Validators.required],
      description: ['', Validators.required],
      measurement_type: ['', Validators.required],
      measurement_amount: ['', Validators.required],
    });
  }  

  // save() { 
  //   this.recipeService.saveFoodGroup(this.addForm.value)
  //   .subscribe(res => {
  //       this.tableData = res
  //     }
  //   )
  // }

  // delete(category: FoodGroupEntity) {
  //   this.recipeService.deleteFoodGroup(category.category_id)
  //   .subscribe(res => {
  //       this.tableData = res
  //     }
  //   )
  // }

}
