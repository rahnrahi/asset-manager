package org.rahi.aseet.services;

import org.rahi.aseet.Entities.Users;

import java.util.List;
import java.util.UUID;

public interface IUsersService {
    List<Users> getUser();
    Users saveUser(Users user);
    Users getUser(UUID userId) throws Exception;
    Users updateUser(UUID userId, Users user) throws Exception;
    String deleteUser(UUID userId);

}
