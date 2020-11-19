import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayAllRecipesComponent } from './recipes.component';

describe('DisplayAllRecipesComponent', () => {
  let component: DisplayAllRecipesComponent;
  let fixture: ComponentFixture<DisplayAllRecipesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisplayAllRecipesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayAllRecipesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
