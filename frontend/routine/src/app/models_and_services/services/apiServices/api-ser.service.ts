import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TrippleAttrNumericDao } from '../../models/dao/TrippleAttrNumericDao';
import { SaveSingleEntityDao } from '../../models/dao/SaveSingleEntityDao';

@Injectable({
  providedIn: 'root'
})
export class ApiSerService {

  constructor(private http: HttpClient) {}


  // DISTINCT TYPES -------------------------------------------------
    // ART 
  private distinct_art_types = 'http://localhost:9001/d/type/art';
  getArtTypes(): Observable<any> {
    return this.http.get(this.distinct_art_types);
  }
    // PROJECT 
  private distinct_project_types = 'http://localhost:9001/d/type/project';
  getProjectTypes(): Observable<any> {
    return this.http.get(this.distinct_project_types);
  }
    // SPORT 
  private distinct_sport_types = 'http://localhost:9001/d/type/sport';
  getSportTypes(): Observable<any> {
    return this.http.get(this.distinct_sport_types);
  }
  // ----------------------------------------------------------------

  // PERFORMANCE ----------------------------------------------------
  private main_p_url = 'http://localhost:9000/main/p';
  // POST Request
  performance(data: TrippleAttrNumericDao): Observable<any> {
    return this.http.post(this.main_p_url, data);
  }
  // ----------------------------------------------------------------

  // SAVE SINGLE ENTITY() -------------------------------------------
  private save_single_url = 'http://localhost:9000/main/saveSingle';
  saveSingleEntityF(data: SaveSingleEntityDao): Observable<any> {
    return this.http.post(this.save_single_url, data);
  }
  // ----------------------------------------------------------------
}
