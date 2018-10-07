import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';

const apiKey = environment.newsApiKey;
const apiUrl = environment.newsApiUrl;

const params = new HttpParams().set('apikey', apiKey);
@Injectable({
  providedIn: 'root'
})

export class NewsService {

  constructor(private http: HttpClient) { }

  getData(url) {
    return this.http.get(`${apiUrl}/${url}`, {params});
  }
}
