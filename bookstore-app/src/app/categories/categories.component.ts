import { Component, OnInit } from '@angular/core';
import { CategoryEntity } from '../models/categories';
import { RecipeService } from '../recipe-service.service';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.scss']
})
export class CategoriesComponent implements OnInit {

  constructor(private recipeService: RecipeService) { }

  categories: CategoryEntity

  ngOnInit() {
    this.recipeService.getCategories().subscribe(data => {
      this.categories = data;
    });
  }

}
