import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InboxExtendComponent } from './inbox-extend.component';

describe('InboxExtendComponent', () => {
  let component: InboxExtendComponent;
  let fixture: ComponentFixture<InboxExtendComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InboxExtendComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InboxExtendComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
