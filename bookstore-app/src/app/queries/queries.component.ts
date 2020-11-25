import { Component, OnInit } from '@angular/core';
import { EquipmentEntity } from '../models/equipment';
import { IngredientsListEntity } from '../models/ingredients-lists';
import { InstructionEntity } from '../models/instructions';
import { RecipeEntity } from '../models/recipes';
import { RecipeService } from '../recipe-service.service';

@Component({
  selector: 'app-queries',
  templateUrl: './queries.component.html',
  styleUrls: ['./queries.component.scss']
})
export class QueriesComponent implements OnInit {;
  recipeData: RecipeEntity[];
  equipmentData: EquipmentEntity[];
  ingredientsData: IngredientsListEntity[];
  instructionsData: InstructionEntity[];

  constructor(private recipeService: RecipeService) { }

  ngOnInit() {
    this.recipeService.getRecipes().subscribe(data => {
      this.recipeData = data;
    });
  }

  getFullRecipe(recipeTitle: String) {
    this.getIngredients(recipeTitle)
    this.getInstructions(recipeTitle)
  }

  getIngredients(recipeTitle: String){
    this.recipeService.customIngredientQuery(recipeTitle)
    .subscribe(res => {
      this.ingredientsData = res
    })
  }

  getInstructions(recipeTitle: String){
    this.recipeService.customInstructionQuery(recipeTitle)
    .subscribe(res => {
      this.instructionsData = res
    })
  }

  ingredientMeasurement(amount: number): number{
    if (amount==0) return null
    else return amount
  }

  ingredientDescription(desc: String): String{
    if (desc=='') return null
    else return '(' + desc + ')'
  }
}
