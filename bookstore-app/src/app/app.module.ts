import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { CategoriesComponent } from './categories/categories.component';
import { RecipesComponent } from './recipes/recipes.component';
import { CreateDbComponent } from './create-db/create-db.component';
import { QueriesComponent } from './queries/queries.component';
import { FoodGroupsComponent } from './food-groups/food-groups.component';
import { IngredientsComponent } from './ingredients/ingredients.component';
import { IngredientsListsComponent } from './ingredients-lists/ingredients-lists.component';
import { InstructionsComponent } from './instructions/instructions.component';
import { EquipmentComponent } from './equipment/equipment.component';

@NgModule({
  declarations: [
    AppComponent,
    RecipesComponent,
    CategoriesComponent,
    CreateDbComponent,
    QueriesComponent,
    FoodGroupsComponent,
    IngredientsComponent,
    IngredientsListsComponent,
    InstructionsComponent,
    EquipmentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
