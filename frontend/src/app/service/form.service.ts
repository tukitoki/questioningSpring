import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import { Form } from '../entity/form';
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class FormService {

  formsUrl: string;

  constructor(private http: HttpClient) {
    this.formsUrl = 'http://localhost:8080/forms'
  }

  public getAll(): Observable<Form[]> {
    return this.http.get<Form[]>(this.formsUrl);
  }

  public getForm(id: number | null): Observable<Form> {
    return this.http.get<Form>(`${this.formsUrl}/${id}/`)
  }

  public save(form: Form): Observable<Form>{
    return this.http.post<Form>(this.formsUrl, form);
  }
  public delete(id: number | null): Observable<void> {
    return this.http.delete<void>(`${this.formsUrl}/${id}/`);
  }
  public update(id: number | null, form: Form): Observable<void> {
    return this.http.put<void>(`${this.formsUrl}/${id}/`, form)
  }
}
