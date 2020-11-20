import { Component, OnInit } from '@angular/core';
import { RecipeEntity } from '../models/recipes';
import {RouterModule, Router} from "@angular/router"
import { RecipeService } from '../recipe-service.service';
import { FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'recipes',
  templateUrl: './recipes.component.html',
  styleUrls: ['./recipes.component.scss']
})
export class RecipesComponent implements OnInit {

  constructor(private router: Router, private recipeService: RecipeService, private formBuilder: FormBuilder) { }

  addForm: FormGroup;
  recipes: RecipeEntity[];
  newRecipe: RecipeEntity;
  deleteId: null;
  deleteResponse: String = ''
  saveResponse: String = ''
  

  ngOnInit() {
    this.recipeService.getRecipes().subscribe(data => {
      this.recipes = data;
    });

    this.addForm = this.formBuilder.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      prep_time: ['', Validators.required],
      cook_time: ['', Validators.required],
      servings: ['', Validators.required]
    });
  }  

  saveRecipe() { 
    this.recipeService.saveRecipe(this.addForm.value)
    .subscribe(res => {
        this.recipes = res
      }
    )
  }

  deleteRecipe() {
    this.recipeService.deleteRecipe(this.deleteId).subscribe(
      res => {
        this.recipes = res
      }
    )
  }
}
