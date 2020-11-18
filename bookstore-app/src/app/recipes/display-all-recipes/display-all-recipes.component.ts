import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router"
import { RecipeService } from '../recipe-service.service';

@Component({
  selector: 'app-display-all-recipes',
  templateUrl: './display-all-recipes.component.html',
  styleUrls: ['./display-all-recipes.component.scss']
})
export class DisplayAllRecipesComponent implements OnInit {

  constructor(private router: Router, private recipeService: RecipeService) { }

  recipes: String[]

  ngOnInit() {
    this.recipeService.getRecipes().subscribe(data => this.recipes = data)
  }

}
