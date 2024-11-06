import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({
  providedIn:'root'
})
export class ProfileService {
  private baseUrl = 'http://localhost:8080/api/user';
  constructor(private http: HttpClient) {}
  getUserProfile(): Observable<any> {
    const token = localStorage.getItem('auth-token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get(`${this.baseUrl}/profile`, { headers });
  }
  updateUserProfile(user: any): Observable<any> {
    const token = localStorage.getItem('auth-token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.put(`${this.baseUrl}/profile`, user, { headers });
  }
}