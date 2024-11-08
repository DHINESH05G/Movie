import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MovieService {
  [x: string]: any;
  private baseUrl = 'http://localhost:8080/api/movies';

  constructor(private http: HttpClient) { }

  getMovies(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
  getMovieById(id: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }
  
}
