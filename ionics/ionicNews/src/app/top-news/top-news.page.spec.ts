import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TopNewsPage } from './top-news.page';

describe('TopNewsPage', () => {
  let component: TopNewsPage;
  let fixture: ComponentFixture<TopNewsPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TopNewsPage ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TopNewsPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
