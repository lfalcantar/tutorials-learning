import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HeadlinesPage } from './headlines.page';

describe('HeadlinesPage', () => {
  let component: HeadlinesPage;
  let fixture: ComponentFixture<HeadlinesPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HeadlinesPage ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HeadlinesPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
