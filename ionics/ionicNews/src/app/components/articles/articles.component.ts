import { Component, OnInit , Input } from '@angular/core';

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.scss']
})
export class ArticlesComponent implements OnInit {

  @Input()news;

  constructor() { }

  ngOnInit() {
  }

}
