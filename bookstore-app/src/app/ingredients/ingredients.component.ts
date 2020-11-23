import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { IngredientEntity } from '../models/ingredients';
import { RecipeService } from '../recipe-service.service';

@Component({
  selector: 'app-ingredients',
  templateUrl: './ingredients.component.html',
  styleUrls: ['./ingredients.component.scss']
})
export class IngredientsComponent implements OnInit {

  constructor(private recipeService: RecipeService, private formBuilder: FormBuilder) { }

  addForm: FormGroup;
  tableData: IngredientEntity[];
  saveResponse: String = ''

  ngOnInit() {
    this.recipeService.getIngredients().subscribe(data => {
      this.tableData = data;
    });

    this.addForm = this.formBuilder.group({
      name: ['', Validators.required],
      food_group: ['', Validators.required],
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
