import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { SessionApiService } from './session-api.service';
import { Session } from '../interfaces/session.interface';

describe('SessionApiService', () => {
  let service: SessionApiService;
  let httpMock: HttpTestingController;

  const mockSession: Session = {
    id: 1,
    name: 'Mock Session',
    description: 'This is a mock session',
    date: new Date(),
    teacher_id: 1,
    users: [1, 2, 3],
    createdAt: new Date(),
    updatedAt: new Date(),
  };

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [SessionApiService]
    });

    service = TestBed.inject(SessionApiService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify(); // Vérifie qu'il n'y a pas de requêtes en attente après chaque test
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should retrieve all sessions via GET', () => {
    service.all().subscribe((sessions) => {
      expect(sessions.length).toBe(1);
      expect(sessions).toEqual([mockSession]);
    });

    const req = httpMock.expectOne('api/session');
    expect(req.request.method).toBe('GET');
    req.flush([mockSession]); // Simule la réponse du serveur avec un tableau de sessions mockées
  });

  it('should retrieve session detail via GET', () => {
    service.detail('1').subscribe((session) => {
      expect(session).toEqual(mockSession);
    });

    const req = httpMock.expectOne('api/session/1');
    expect(req.request.method).toBe('GET');
    req.flush(mockSession); // Simule la réponse du serveur avec une session mockée
  });

  it('should delete a session via DELETE', () => {
    service.delete('1').subscribe((response) => {
      expect(response).toBeTruthy(); // Réponse vide ou confirmation de suppression
    });

    const req = httpMock.expectOne('api/session/1');
    expect(req.request.method).toBe('DELETE');
    req.flush({}); // Simule une réponse vide pour la suppression
  });

  it('should create a session via POST', () => {
    service.create(mockSession).subscribe((session) => {
      expect(session).toEqual(mockSession);
    });

    const req = httpMock.expectOne('api/session');
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(mockSession);
    req.flush(mockSession); // Simule la réponse du serveur avec la session créée
  });

  it('should update a session via PUT', () => {
    service.update('1', mockSession).subscribe((session) => {
      expect(session).toEqual(mockSession); // Vérifie que la session renvoyée correspond à celle envoyée
    });

    const req = httpMock.expectOne('api/session/1');
    expect(req.request.method).toBe('PUT'); // Vérifie que la méthode HTTP utilisée est bien PUT
    expect(req.request.body).toEqual(mockSession); // Vérifie que le corps de la requête contient bien les données de la session
    req.flush(mockSession); // Simule une réponse avec la session mise à jour
  });

});
