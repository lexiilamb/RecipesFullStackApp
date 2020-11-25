import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { CategoriesComponent } from './categories/categories.component';
import { CreateDbComponent } from './create-db/create-db.component';
import { EquipmentComponent } from './equipment/equipment.component';
import { FoodGroupsComponent } from './food-groups/food-groups.component';
import { IngredientsListsComponent } from './ingredients-lists/ingredients-lists.component';
import { IngredientsComponent } from './ingredients/ingredients.component';
import { InstructionsComponent } from './instructions/instructions.component';
import { QueriesComponent } from './queries/queries.component';
import { RecipesComponent } from './recipes/recipes.component';


const routes: Routes = [
  {path: 'full-recipes', pathMatch: 'full', component: QueriesComponent},
  {path: 'create-db', pathMatch: 'full', component: CreateDbComponent},
  {path: 'recipes', pathMatch: 'full', component: RecipesComponent},
  {path: 'categories', pathMatch: 'full', component: CategoriesComponent},
  {path: 'food-groups', pathMatch: 'full', component: FoodGroupsComponent},
  {path: 'ingredients-lists', pathMatch: 'full', component: IngredientsListsComponent},
  {path: 'ingredients', pathMatch: 'full', component: IngredientsComponent},
  {path: 'instructions', pathMatch: 'full', component: InstructionsComponent},
  {path: 'equipment', pathMatch: 'full', component: EquipmentComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
