import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import { RecipeEntity } from './models/recipes';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  private url = 'http://localhost:8090/';

  constructor(private http: HttpClient) {
  }

  getRecipes(): Observable<any> {
    return this.http.get(`${this.url}/recipes`);
  }

  saveRecipe(recipe: RecipeEntity) {
    this.http.post(`${this.url}/addRecipe`, recipe);
  }

  // addRecipe(recipe: Object): Observable<Object> {
  //   return this.http.post(`${this.url}`, recipe);
  // }

  // deleteRecipe(id: number): Observable<any> {
  //   return this.http.delete(`${this.url}/${id}`, {responseType: 'text'});
  // }

  getCategories(): Observable<any> {
    return this.http.get(`${this.url}/categories`);
  }
}