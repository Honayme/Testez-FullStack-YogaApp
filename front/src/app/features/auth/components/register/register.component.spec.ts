import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { expect } from '@jest/globals';
import { AuthService } from '../../services/auth.service';

import { RegisterComponent } from './register.component';
import {of} from "rxjs";

describe('RegisterComponent', () => {
  let component: RegisterComponent;
  let fixture: ComponentFixture<RegisterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RegisterComponent],
      imports: [
        BrowserAnimationsModule,
        HttpClientModule,
        ReactiveFormsModule,
        MatCardModule,
        MatFormFieldModule,
        MatIconModule,
        MatInputModule
      ]
    })
      .compileComponents();

    fixture = TestBed.createComponent(RegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  // Check if form contains 4 required controls
  it('should have a form with 4 controls', () => {
    expect(component.form.contains('email')).toBeTruthy();
    expect(component.form.contains('firstName')).toBeTruthy();
    expect(component.form.contains('lastName')).toBeTruthy();
    expect(component.form.contains('password')).toBeTruthy();
  });

// Ensure the email control is required
  it('should make the email control required', () => {
    const control = component.form.get('email');
    control?.setValue('');
    expect(control?.valid).toBeFalsy();
  });

// Ensure the firstName control is required
  it('should make the firstName control required', () => {
    const control = component.form.get('firstName');
    control?.setValue('');
    expect(control?.valid).toBeFalsy();
  });

// Ensure the lastName control is required
  it('should make the lastName control required', () => {
    const control = component.form.get('lastName');
    control?.setValue('');
    expect(control?.valid).toBeFalsy();
  });

// Ensure the password control is required
  it('should make the password control required', () => {
    const control = component.form.get('password');
    control?.setValue('');
    expect(control?.valid).toBeFalsy();
  });

// Verify error handling if user registration fails
  it('should register user with error if the register request fails', () => {
    const authService = TestBed.inject(AuthService);
    const spy = jest.spyOn(authService, 'register').mockReturnValue(of());
    component.submit();

    expect(spy).toHaveBeenCalled();
  });

});
