import { Component, OnInit } from '@angular/core';
import { RecipeEntity } from '../models/recipes';
import { RecipeService } from '../recipe-service.service';

@Component({
  selector: 'app-queries',
  templateUrl: './queries.component.html',
  styleUrls: ['./queries.component.scss']
})
export class QueriesComponent implements OnInit {
  responseRecipe: RecipeEntity[];
  responseIngredient;
  queryr: String = "";
  queryi: String = "";

  constructor(private recipeService: RecipeService) { }

  ngOnInit(): void {
  }

  runRecipeQuery(){
    this.recipeService.customRecipeQuery(this.queryr)
    .subscribe(res => {
      console.log(res)
      this.responseRecipe = res
    })
  }

  runIngredientQuery(){
    this.recipeService.customIngredientQuery(this.queryi)
    .subscribe(res => {
      console.log(res)
      this.responseIngredient = res
    })
  }

}
