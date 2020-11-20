import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { CategoriesComponent } from './categories/categories.component';
import { CreateDbComponent } from './create-db/create-db.component';
import { RecipesComponent } from './recipes/recipes.component';


const routes: Routes = [
  {path: 'create-db', pathMatch: 'full', component: CreateDbComponent},
  {path: 'recipes', pathMatch: 'full', component: RecipesComponent},
  {path: 'categories', pathMatch: 'full', component: CategoriesComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
