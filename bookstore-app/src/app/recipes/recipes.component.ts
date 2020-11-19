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
  newRecipe: RecipeEntity
  title: string
  description: string
  prep_time: number
  cook_time: number
  servings: number
  

  ngOnInit() {
    this.recipeService.getRecipes().subscribe(data => {
      this.recipes = data;
    });
    // this.router.events.subscribe(value => {
    //   this.getRecipes();
    // });

    // this.addForm = this.formBuilder.group({
    //   title: ['', Validators.required],
    //   description: ['', Validators.required],
    //   prep_time: ['', Validators.required],
    //   cook_time: ['', Validators.required],
    //   servings: ['', Validators.required]
    // });
  }  

    addRecipe(): void {
      this.router.navigate(['add-recipe'])
      .then((e) => {
        if(e){
          console.log("Success! Saved recipe")
        } else {
          console.log("Recipe save failed")
        }
      })
    }

    saveRecipe() {    
      this.newRecipe.title = this.title 
      this.newRecipe.description = this.description 
      this.newRecipe.prep_time = this.prep_time 
      this.newRecipe.cook_time = this.cook_time 
      this.newRecipe.servings = this.servings 

      this.recipeService.saveRecipe(this.newRecipe)
        // .subscribe(data => {
        //   this.router.navigate(['recipes']);
        // });
    }
}
