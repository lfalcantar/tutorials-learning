import { NewsService } from './../services/news.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-top-news',
  templateUrl: './top-news.page.html',
  styleUrls: ['./top-news.page.scss'],
})
export class TopNewsPage implements OnInit {
  news: any;

  constructor(private newsService: NewsService) { }

  ngOnInit() {
    this.news = this.newsService.getData('top-headlines?country=us&category=business');
  }
}
