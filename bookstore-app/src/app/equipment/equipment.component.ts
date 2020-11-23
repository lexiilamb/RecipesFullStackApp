import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EquipmentEntity } from '../models/equipment';
import { RecipeService } from '../recipe-service.service';

@Component({
  selector: 'app-equipment',
  templateUrl: './equipment.component.html',
  styleUrls: ['./equipment.component.scss']
})
export class EquipmentComponent implements OnInit {

  constructor(private recipeService: RecipeService, private formBuilder: FormBuilder) { }

  addForm: FormGroup;
  tableData: EquipmentEntity[];
  saveResponse: String = ''

  ngOnInit() {
    this.recipeService.getEquipment().subscribe(data => {
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
