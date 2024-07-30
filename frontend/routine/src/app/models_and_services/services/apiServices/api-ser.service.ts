import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiSerService {

  constructor(private http: HttpClient) {}


  // Record Archive Api
  //GET 
  
  getData_record_archive(): Observable<any> {
    return this.http.get(this.sportI);
  }

  private sportI = 'http://localhost:9000/i/sportP';
  // POST Request
  postSportPerformance(data: any): Observable<any> {
    return this.http.post(this.sportI, data);
  }
}
