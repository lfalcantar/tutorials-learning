import { SourcesPageModule } from './../sources/sources.module';
import { SettingsPageModule } from './../settings/settings.module';
import { FavoritesPageModule } from './../favorites/favorites.module';
import { TopNewsPageModule } from './../top-news/top-news.module';
import { HeadlinesPageModule } from '../headlines/headlines.module';
import { IonicModule } from '@ionic/angular';
import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { TabsPageRoutingModule } from './tabs.router.module';

import { TabsPage } from './tabs.page';

@NgModule({
  imports: [
    IonicModule,
    CommonModule,
    FormsModule,
    TabsPageRoutingModule,
    TopNewsPageModule,
    HeadlinesPageModule,
    FavoritesPageModule,
    SettingsPageModule,
    SourcesPageModule,

  ],
  declarations: [TabsPage]
})
export class TabsPageModule {}
