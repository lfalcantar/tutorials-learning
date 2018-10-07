import { HttpClient } from '@angular/common/http';
import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';

const apiKey = environment.newsApiKey;
const apiUrl = environment.newsApiUrl;

@Injectable({
  providedIn: 'root'
})

export class NewsService {

  constructor(private http: HttpClient) { }

  getData(url) {
    return this.http.get(`${apiUrl}/${url}apikey=${apiKey}`);
  }
}
