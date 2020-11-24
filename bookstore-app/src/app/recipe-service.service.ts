import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import { FormGroup } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  private url = 'http://localhost:8090';

  constructor(private http: HttpClient) {
  }
  
  customRecipeQuery(query: String): Observable<any> {
    return this.http.post(`${this.url};/r-query`, query);
  }
  
  customIngredientQuery(query: String): Observable<any> {
    return this.http.post(`${this.url}/i-query`, query);
  }

  reCreateDatabases(): Observable<any> {
    return this.http.get(`${this.url}`, {responseType: 'text'});
  }

  getRecipes(): Observable<any> {
    return this.http.get(`${this.url}/recipes`);
  }

  saveRecipe(form: FormGroup): Observable<any> {
    return this.http.post(`${this.url}/recipes`, form);
  }

  deleteRecipe(id: number): Observable<any> {
    return this.http.delete(`${this.url}/recipes/${id}`);
  }

  getCategories(): Observable<any> {
    return this.http.get(`${this.url}/categories`);
  }

  saveCategory(form: FormGroup): Observable<any> {
    return this.http.post(`${this.url}/categories`, form);
  }

  deleteCateogry(id: number): Observable<any> {
    return this.http.delete(`${this.url}/categories/${id}`);
  }

  getFoodGroups(): Observable<any> {
    return this.http.get(`${this.url}/food-groups`);
  }

  // saveFoodGroup(form: FormGroup): Observable<any> {
  //   return this.http.post(`${this.url}/food-groups`, form);
  // }

  // deleteFoodGroup(id: number): Observable<any> {
  //   return this.http.delete(`${this.url}/food-groups/${id}`);
  // }

  getIngredients(): Observable<any> {
    return this.http.get(`${this.url}/ingredients`);
  }

  // saveIngredient(form: FormGroup): Observable<any> {
  //   return this.http.post(`${this.url}/ingredient`, form);
  // }

  // deleteIngredient(id: number): Observable<any> {
  //   return this.http.delete(`${this.url}/ingredient/${id}`);
  // }

  getIngredientsLists(): Observable<any> {
    return this.http.get(`${this.url}/ingredients-lists`);
  }

  // saveIngredientsList(form: FormGroup): Observable<any> {
  //   return this.http.post(`${this.url}/ingredients-lists`, form);
  // }

  // deleteIngredientsList(id: number): Observable<any> {
  //   return this.http.delete(`${this.url}/ingredients-lists/${id}`);
  // }

  getInstructions(): Observable<any> {
    return this.http.get(`${this.url}/instructions`);
  }

  getEquipment(): Observable<any> {
    return this.http.get(`${this.url}/equipment`);
  }
}