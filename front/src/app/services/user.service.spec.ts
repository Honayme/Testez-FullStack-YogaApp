import {TestBed} from '@angular/core/testing';
import {expect} from '@jest/globals';

import {UserService} from './user.service';
import {User} from "../interfaces/user.interface";
import {HttpClientTestingModule, HttpTestingController} from "@angular/common/http/testing";

describe('UserService', () => {
  const pathService = 'api/user';

  let service: UserService;
  let httpTesting: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule
      ],
      providers: [UserService]
    });
    service = TestBed.inject(UserService);
    httpTesting = TestBed.inject(HttpTestingController);

  });

  // Ensures no pending HTTP requests remain after each test.
  afterEach(() => {
    httpTesting.verify();
  });


  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  // Test for getById method
  it('should get User by ID', () => {
    const id = 123; // L'ID est un nombre

    const mockUser: User = {
      id: id, // Utilisation de la constante id dans mockUser
      email: 'johndoe@example.com',
      lastName: 'Doe',
      firstName: 'John',
      admin: true,
      password: 'password123',
      createdAt: new Date(),
      updatedAt: new Date(),
    };

    service.getById(id.toString()).subscribe((user) => {
      expect(user).toEqual(mockUser);
    });

    const req = httpTesting.expectOne(`${pathService}/${id}`);
    expect(req.request.method).toBe('GET');
    req.flush(mockUser);
  });

  // Test for delete method
  it('should delete User by ID', () => {
    const id = 123; // ID est un nombre

    service.delete(id.toString()).subscribe((response) => {
      expect(response).toBeNull(); // Le service delete retourne une réponse vide (null)
    });

    const req = httpTesting.expectOne(`${pathService}/${id}`);
    expect(req.request.method).toBe('DELETE');
    req.flush(null); // Simuler une réponse vide pour la requête DELETE
  });

});
