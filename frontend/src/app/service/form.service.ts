import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import { Form } from '../entity/form';
import {Observable} from "rxjs";
import { Page } from '../entity/page';

@Injectable({
  providedIn: 'root'
})
export class FormService {

  formsUrl: string;

  constructor(private http: HttpClient) {
    this.formsUrl = 'http://localhost:8080/forms'
  }

  public getAll(description: string = '', page: number = 0, size: number = 10): Observable<Page<Form>> {
    let params = {
      'description': description,
      'page': page,
      'size': size
    };
    return this.http.get<Page<Form>>(`${this.formsUrl}`, { params: params });
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
