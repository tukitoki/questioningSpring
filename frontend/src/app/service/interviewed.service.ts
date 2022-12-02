import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {HttpClient} from "@angular/common/http";
import { Interviewed } from '../entity/interviewed';

@Injectable({
  providedIn: 'root'
})
export class InterviewedService {

  attemptUrl: string;

  constructor(private http: HttpClient) {
    this.attemptUrl = 'http://localhost:8080/attempts'
  }

  public save(interviwerd: Interviewed): Observable<Interviewed> {
    return this.http.post<Interviewed>(this.attemptUrl, interviwerd);
  }
  
  public getAllAttempts(): Observable<Interviewed[]> {
    return this.http.get<Interviewed[]>(this.attemptUrl);
  }

  public getAttempt(id: number): Observable<Interviewed> {
    return this.http.get<Interviewed>(`${this.attemptUrl}/${id}/`)
  }
}
