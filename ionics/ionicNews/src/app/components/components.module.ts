import { IonicModule } from '@ionic/angular';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ArticlesComponent } from './articles/articles.component';
import { ArticleComponent } from './article/article.component';

@NgModule({
  imports: [
    CommonModule,
    IonicModule
  ],
  declarations: [ArticlesComponent, ArticleComponent],
  exports: [ArticlesComponent]
})
export class ComponentsModule { }
