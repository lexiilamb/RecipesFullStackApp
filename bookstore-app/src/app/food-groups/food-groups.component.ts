import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FoodGroupEntity } from '../models/food-groups';
import { RecipeService } from '../recipe-service.service';

@Component({
  selector: 'app-food-groups',
  templateUrl: './food-groups.component.html',
  styleUrls: ['./food-groups.component.scss']
})
export class FoodGroupsComponent implements OnInit {

  constructor(private recipeService: RecipeService, private formBuilder: FormBuilder) { }

  addForm: FormGroup;
  tableData: FoodGroupEntity[];
  saveResponse: String = ''

  ngOnInit() {
    this.recipeService.getFoodGroups().subscribe(data => {
      this.tableData = data;
    });

    this.addForm = this.formBuilder.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
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
