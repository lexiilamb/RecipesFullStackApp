import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { InstructionEntity } from '../models/instructions';
import { RecipeService } from '../recipe-service.service';

@Component({
  selector: 'app-instructions',
  templateUrl: './instructions.component.html',
  styleUrls: ['./instructions.component.scss']
})
export class InstructionsComponent implements OnInit {

  constructor(private recipeService: RecipeService, private formBuilder: FormBuilder) { }

  addForm: FormGroup;
  tableData: InstructionEntity[];
  saveResponse: String = ''

  ngOnInit() {
    this.recipeService.getInstructions().subscribe(data => {
      this.tableData = data;
    });

    this.addForm = this.formBuilder.group({
      recipe_id: ['', Validators.required],
      step: ['', Validators.required],
      instruction: ['', Validators.required],
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
