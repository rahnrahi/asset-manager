package org.rahi.aseet.services;

import org.rahi.aseet.Entities.User;

import java.util.List;
import java.util.UUID;

public interface IUsersService {
    List<User> getUser();
    User saveUser(User user);
    User getUser(UUID userId) throws Exception;
    User updateUser(UUID userId, User user) throws Exception;
    String deleteUser(UUID userId);

}
