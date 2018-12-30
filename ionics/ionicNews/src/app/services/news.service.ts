import { IonicModule } from '@ionic/angular';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { LoadingController } from '@ionic/angular';
import { tap } from 'rxjs/operators';

const apiKey = environment.newsApiKey;
const apiUrl = environment.newsApiUrl;
const params = new HttpParams().set('apikey', apiKey);
@Injectable({
  providedIn: 'root'
})

export class NewsService {
  loading;
  constructor(
    private http: HttpClient,
    public loadingController: LoadingController
  ) {}

  async showLoading() {
    this.loading = await this.loadingController.create({
      duration: 2000
    });

    return await this.loading.present();
  }

  getData(url) {
    this.showLoading();
    return this.http.get(`${apiUrl}/${url}`, { params }).pipe(
      tap(value => {
        if (this.loading) {
          this.loading.dismiss();
        }
        console.log(value);
      })
    );
  }
}
