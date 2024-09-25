import { TestBed } from '@angular/core/testing';
import { SessionService } from './session.service';
import { SessionInformation } from '../interfaces/sessionInformation.interface';
import { Observable } from 'rxjs';

describe('SessionService', () => {
  let service: SessionService;

  // Création de mockSessionUser à utiliser dans les tests
  const mockSessionUser: SessionInformation = {
    token: 'abc123',
    type: 'Bearer',
    id: 1,
    username: 'johndoe',
    firstName: 'John',
    lastName: 'Doe',
    admin: true
  };

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SessionService);
  });

  // Vérifie que le service est correctement créé
  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  // Teste l'état initial de isLogged (il doit être false)
  it('should start with isLogged as false', () => {
    expect(service.isLogged).toBe(false);
  });

  // Teste l'Observable $isLogged() pour vérifier qu'il retourne un Observable et que la valeur initiale est correcte
  it('should return an Observable for $isLogged()', () => {
    expect(service.$isLogged()).toBeInstanceOf(Observable);
  });

  // Teste l'état initial de sessionInformation (doit être undefined)
  it('should initialize sessionInformation as undefined', () => {
    expect(service.sessionInformation).toBeUndefined();
  });

  // Teste la méthode logIn et vérifie si l'utilisateur est bien connecté
  it('should log in and set sessionInformation and isLogged to true', (done) => {
    service.logIn(mockSessionUser);

    expect(service.isLogged).toBe(true);  // Vérifie que l'utilisateur est connecté
    expect(service.sessionInformation).toEqual(mockSessionUser);  // Vérifie que les informations de session sont bien mises à jour

    // Vérifie que l'Observable $isLogged() émet correctement true
    service.$isLogged().subscribe((isLogged) => {
      expect(isLogged).toBe(true);
      done();
    });
  });

  // Teste la méthode logOut et vérifie si l'utilisateur est bien déconnecté
  it('should log out and set sessionInformation to undefined and isLogged to false', (done) => {
    service.logIn(mockSessionUser); // Connecter l'utilisateur d'abord
    service.logOut(); // Puis déconnecter

    expect(service.isLogged).toBe(false);  // Vérifie que l'utilisateur est déconnecté
    expect(service.sessionInformation).toBeUndefined();  // Vérifie que sessionInformation est bien réinitialisé

    // Vérifie que l'Observable $isLogged() émet correctement false
    service.$isLogged().subscribe((isLogged) => {
      expect(isLogged).toBe(false);
      done();
    });
  });

  // Teste la méthode logIn pour un autre utilisateur et vérifie que les informations de session sont mises à jour correctement
  it('should update sessionInformation correctly on logIn', () => {
    const anotherMockUser: SessionInformation = {
      token: 'xyz456',
      type: 'Bearer',
      id: 2,
      username: 'janesmith',
      firstName: 'Jane',
      lastName: 'Smith',
      admin: false
    };

    service.logIn(anotherMockUser);

    expect(service.isLogged).toBe(true);
    expect(service.sessionInformation).toEqual(anotherMockUser);
  });

  // Teste la méthode logOut pour vérifier que les informations sont bien réinitialisées
  it('should clear sessionInformation on logOut', () => {
    service.logIn(mockSessionUser);
    service.logOut();

    expect(service.sessionInformation).toBeUndefined();
    expect(service.isLogged).toBe(false);
  });
});
