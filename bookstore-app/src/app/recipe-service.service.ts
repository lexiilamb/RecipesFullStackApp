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

}