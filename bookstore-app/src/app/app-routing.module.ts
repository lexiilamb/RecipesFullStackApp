import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DisplayAllRecipesComponent } from './recipes/display-all-recipes/display-all-recipes.component';


const routes: Routes = [
  {path: 'app-display-all-recipes', pathMatch: 'full', component: DisplayAllRecipesComponent}
  // {path: '', redirectTo: 'app-display-all-recipes', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
