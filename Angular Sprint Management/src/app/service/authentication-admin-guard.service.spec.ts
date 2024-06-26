import { TestBed } from '@angular/core/testing';

import { AuthenticationAdminGuardService } from './authentication-admin-guard.service';

describe('AuthenticationAdminGuardService', () => {
  let service: AuthenticationAdminGuardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthenticationAdminGuardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
