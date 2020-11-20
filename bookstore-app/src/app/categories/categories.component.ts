import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CategoryEntity } from '../models/categories';
import { RecipeService } from '../recipe-service.service';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.scss']
})
export class CategoriesComponent implements OnInit {

  constructor(private router: Router, private recipeService: RecipeService, private formBuilder: FormBuilder) { }

  addForm: FormGroup;
  tableData: CategoryEntity[];
  deleteId: null;
  deleteResponse: String = ''
  saveResponse: String = ''
  

  ngOnInit() {
    this.recipeService.getCategories().subscribe(data => {
      this.tableData = data;
    });

    this.addForm = this.formBuilder.group({
      category: ['', Validators.required],
      recipe_id: ['', Validators.required],
    });
  }  

  save() { 
    this.recipeService.saveCategory(this.addForm.value)
    .subscribe(res => {
        this.tableData = res
      }
    )
  }

  delete() {
    this.recipeService.deleteCateogry(this.deleteId)
    .subscribe(res => {
        this.tableData = res
      }
    )
  }
}
