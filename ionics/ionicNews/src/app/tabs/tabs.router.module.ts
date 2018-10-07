import { FavoritesPage } from './../favorites/favorites.page';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { TabsPage } from './tabs.page';
import { HomePage } from '../home/home.page';
import { AboutPage } from '../about/about.page';
import { ContactPage } from '../contact/contact.page';
import { TopNewsPage } from '../top-news/top-news.page';
import { HeadlinesPage } from '../headlines/headlines.page';
import { SettingsPage } from '../settings/settings.page';
import { SourcesPage } from '../sources/sources.page';

const routes: Routes = [
  {
    path: 'tabs',
    component: TabsPage,
    children: [
      {
        path: '',
        redirectTo: '/tabs/(top-news:top-news)',
        pathMatch: 'full',
      },
      {
        path: 'top-news',
        outlet: 'top-news',
        component: TopNewsPage
      },
      {
        path: 'favorites',
        outlet: 'favorites',
        component: FavoritesPage
      },
      {
        path: 'headlines',
        outlet: 'headlines',
        component: HeadlinesPage
      },
      {
        path: 'settings',
        outlet: 'settings',
        component: SettingsPage
      },
      {
        path: 'sources',
        outlet: 'sources',
        component: SourcesPage
      }
    ]
  },
  {
    path: '',
    redirectTo: '/tabs/(top-news:top-news)',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TabsPageRoutingModule {}
