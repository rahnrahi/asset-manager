package org.rahi.aseet.services.interfaces;

import org.rahi.aseet.Entities.UserAccountEntity;
import org.rahi.aseet.payload.request.SignUpRequest;

import java.util.List;
import java.util.UUID;

public interface IUsersService {
    List<UserAccountEntity> getUser();
    UserAccountEntity saveUser(SignUpRequest user);
    UserAccountEntity getUser(UUID userId) throws Exception;
    UserAccountEntity updateUser(UUID userId, UserAccountEntity user) throws Exception;
    String deleteUser(UUID userId);

}
