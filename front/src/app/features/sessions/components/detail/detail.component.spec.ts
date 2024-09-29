import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import {FormBuilder, ReactiveFormsModule} from '@angular/forms';
import {MatSnackBar, MatSnackBarModule} from '@angular/material/snack-bar';
import { RouterTestingModule, } from '@angular/router/testing';
import { expect } from '@jest/globals';
import { SessionService } from '../../../../services/session.service';

import { DetailComponent } from './detail.component';
import {Session} from "../../interfaces/session.interface";
import {ActivatedRoute, Router} from "@angular/router";
import { SessionInformation } from 'src/app/interfaces/sessionInformation.interface';
import { Teacher } from 'src/app/interfaces/teacher.interface';
import {of} from "rxjs";
import {SessionApiService} from "../../services/session-api.service";
import {TeacherService} from "../../../../services/teacher.service";
import {MatCardModule} from "@angular/material/card";
import {MatIconModule} from "@angular/material/icon";


describe('DetailComponent', () => {
  const sessionInformation: SessionInformation = { token: '', type: '', id: 1, username: '', firstName: '', lastName: '', admin: true };


  let component: DetailComponent;
  let fixture: ComponentFixture<DetailComponent>;

  const teacher: Teacher = { id: 1, lastName: 'Doe', firstName: 'John', createdAt: new Date(), updatedAt: new Date()};

  let teacherService: jest.Mocked<TeacherService>;
  let matSnackBar: jest.Mocked<MatSnackBar>;

  let activatedRoute: jest.Mocked<ActivatedRoute>;
  let router: jest.Mocked<Router>;
  let session: jest.Mocked<Session>;

  let sessionApiService: jest.Mocked<SessionApiService>;
  let sessionService: jest.Mocked<SessionService>;

  beforeEach(async () => {

    session = {
      id: 1,
      users: [0],
      teacher_id: 2
    } as any as jest.Mocked<Session>;

    router = {
      navigate: jest.fn()
    } as any as jest.Mocked<Router>;

    matSnackBar = {
      open: jest.fn()
    } as any as jest.Mocked<MatSnackBar>;

    sessionService = {
      sessionInformation: sessionInformation
    } as any as jest.Mocked<SessionService>;

    sessionApiService = {
      delete: jest.fn(),
      participate: jest.fn(),

      unParticipate: jest.fn(),
      detail: jest.fn().mockReturnValue(of(session))
    } as any as jest.Mocked<SessionApiService>;

    teacherService = {
      detail: jest.fn(),
    } as any as jest.Mocked<TeacherService>;

    activatedRoute = {
      snapshot: {
        paramMap: {
          get: jest.fn().mockReturnValue('1'),
        },
      },
    } as unknown as jest.Mocked<ActivatedRoute>;

    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        HttpClientModule,

        MatSnackBarModule,
        ReactiveFormsModule,
        MatCardModule,


        MatIconModule
      ],
      declarations: [DetailComponent],
      providers: [
        FormBuilder,
        { provide: MatSnackBar, useValue: matSnackBar },
        { provide: Router, useValue: router },
        { provide: SessionService, useValue: sessionService },
        { provide: ActivatedRoute, useValue: activatedRoute },
        { provide: SessionApiService, useValue: sessionApiService },
        { provide: TeacherService, useValue: teacherService },

      ],
    }).compileComponents();

    fixture = TestBed.createComponent(DetailComponent);

    component = fixture.componentInstance;

    fixture.detectChanges();

  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  describe('back', () => {
    it('should call windows.history.back()', () => {
      const backSpy = jest.spyOn(window.history, 'back');
      component.back();
      expect(backSpy).toHaveBeenCalledTimes(1);
    });
  });


  describe('unParticipate', () => {
    it('should call sessionApiService.unParticipate on unParticipate', () => {
      const unParticipeSpy =  jest.spyOn(sessionApiService, 'unParticipate').mockReturnValue(of(void 0));

      component.unParticipate();

      expect(unParticipeSpy).toHaveBeenCalledWith(session.id?.toString(), sessionInformation.id.toString());
    });
  });


  describe('delete', () => {
    it('should call sessionApiService.delete and router.navigate on delete', () => {
      const sessionApiServiceSpy = jest.spyOn(sessionApiService, 'delete').mockReturnValue(of(null));

      component.delete();

      expect(sessionApiServiceSpy).toHaveBeenCalledWith('1');
      expect(matSnackBar.open).toHaveBeenCalledWith('Session deleted !', 'Close', { duration: 3000 });

      expect(router.navigate).toHaveBeenCalledWith(['sessions']);
    });
  });

  describe('ngOnInit', () => {
    it('should call sessionApiService.detail and teacherService.detail on ngOnInit', () => {
      const SessionApiServiceSpy = jest.spyOn(sessionApiService, 'detail').mockReturnValue(of(session));
      const TeacherServiceSpy = jest.spyOn(teacherService, 'detail').mockReturnValue(of(teacher));

      component.ngOnInit();

      expect(SessionApiServiceSpy).toHaveBeenCalledWith('1');
      expect(TeacherServiceSpy).toHaveBeenCalledWith('2');
      expect(component.session).toEqual(session);

      expect(component.teacher).toEqual(teacher);
      expect(component.isParticipate).toBeFalsy();
    });
  });

  describe('participate', () => {
    it('should call sessionApiService.participate on participate', () => {
      const participeSpy =  jest.spyOn(sessionApiService, 'participate').mockReturnValue(of(void 0));

      component.participate();

      expect(participeSpy).toHaveBeenCalledWith(session.id?.toString(), sessionInformation.id.toString());
    });
  });

});

