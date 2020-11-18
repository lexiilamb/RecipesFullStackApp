import {Component, OnInit} from '@angular/core';
import { Recipe } from './models/recipes';
import { RecipeService } from './recipes/recipe-service.service';
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'RecipesDB';
  recipes: Recipe[];


  constructor(private router: Router, private recipeService: RecipeService) {
  }

  // getRecipes() {
  //   this.recipeService.getRecipes().subscribe(data => {
  //     this.recipes = data;
  //   });
  // }

  ngOnInit() {
    // this.router.events.subscribe(value => {
    //   this.getRecipes();
    // });
  }
}