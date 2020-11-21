import { Component, OnInit } from '@angular/core';
import { RecipeService } from '../recipe-service.service';

@Component({
  selector: 'app-queries',
  templateUrl: './queries.component.html',
  styleUrls: ['./queries.component.scss']
})
export class QueriesComponent implements OnInit {
  response;
  query: String = "";

  constructor(private recipeService: RecipeService) { }

  ngOnInit(): void {
  }

  runQuery(){
    this.recipeService.customQuery(this.query)
    .subscribe(res => {
      console.log(res)
      this.response = res
    })
  }

}
