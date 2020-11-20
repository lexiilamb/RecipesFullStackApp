import {Component, OnInit} from '@angular/core';
import { RecipeService } from './recipe-service.service';
import {Router, ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'RecipesDB';
  recipes;


  constructor(private router: Router, private route: ActivatedRoute, private recipeService: RecipeService) {
  }

  ngOnInit() {
  }
}