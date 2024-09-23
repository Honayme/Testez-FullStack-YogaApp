import {TestBed} from '@angular/core/testing';
import {expect} from '@jest/globals';

import {TeacherService} from './teacher.service';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import {Teacher} from "../interfaces/teacher.interface";


describe('TeacherService', () => {
  const path = 'api/teacher';

  let service: TeacherService;
  let httpTesting: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule
      ]
    });
    service = TestBed.inject(TeacherService);
    httpTesting = TestBed.inject(HttpTestingController);
  });

  // Ensures no pending HTTP requests remain after each test.
  afterEach(() => {
    httpTesting.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  // Verifies that the 'all' method fetches all teachers via a GET request.
  it('should get All Teacher', () => {
    const mockTeacher = {
      lastName: 'John',
      firstName: 'Doe',
      createdAt: new Date(),
      updatedAt: new Date(),
    };

    service.all().subscribe((teachers) => {
      expect(teachers).toEqual([mockTeacher]);
    });

    const req = httpTesting.expectOne(path);
    expect(req.request.method).toBe('GET');
    req.flush([mockTeacher]);
  });

  // Verifies that the 'detail' method fetches a specific teacher by ID via a GET request.
  it('should get Teacher detail by ID', () => {
    const id = 123;

    const mockTeacher: Teacher = {
      id: id,
      lastName: 'John',
      firstName: 'Doe',
      createdAt: new Date(),
      updatedAt: new Date(),
    };

    service.detail(id.toString()).subscribe((teacher) => {
      expect(teacher).toEqual(mockTeacher);
    });

    const req = httpTesting.expectOne(`${path}/${id}`);
    expect(req.request.method).toBe('GET');
    req.flush(mockTeacher);
  });

});
