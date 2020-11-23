import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IngredientsListsComponent } from './ingredients-lists.component';

describe('IngredientsListsComponent', () => {
  let component: IngredientsListsComponent;
  let fixture: ComponentFixture<IngredientsListsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IngredientsListsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IngredientsListsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
