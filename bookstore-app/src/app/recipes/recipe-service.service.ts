import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  private url = 'http://localhost:8090/recipes/';

  constructor(private http: HttpClient) {
  }

  getRecipes(): Observable<any> {
    console.log('here: '+ this.http.get(`${this.url}`))
    return this.http.get(`${this.url}`);
  }

  // addRecipe(recipe: Object): Observable<Object> {
  //   return this.http.post(`${this.url}`, recipe);
  // }

  // deleteRecipe(id: number): Observable<any> {
  //   return this.http.delete(`${this.url}/${id}`, {responseType: 'text'});
  // }
}