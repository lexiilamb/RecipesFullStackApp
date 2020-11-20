import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RecipeService } from '../recipe-service.service';

@Component({
  selector: 'app-create-db',
  templateUrl: './create-db.component.html',
  styleUrls: ['./create-db.component.scss']
})
export class CreateDbComponent implements OnInit {
  response;

  constructor(private router: Router, private recipeService: RecipeService) { }

  ngOnInit(): void {
  }

  reCreateDatabase(){
    this.recipeService.reCreateDatabases()
    .subscribe(res => {
      this.response = res
    }
  )
  }

}
