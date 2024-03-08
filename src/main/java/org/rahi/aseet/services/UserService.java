package org.rahi.aseet.services;

import org.rahi.aseet.Entities.User;
import org.rahi.aseet.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class UserService implements IUsersService, UserDetailsService {

    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public List<User> getUser() {
        return iUserRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        return iUserRepository.save(user);
    }

    @Override
    public User getUser(UUID userId) throws Exception {
        var userDetails = iUserRepository.findById(userId);
        if(userDetails.isEmpty()){
            throw new Exception("User not found");
        }
        return userDetails.get();
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = iUserRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }

    @Override
    public User updateUser(UUID userId, User user) throws Exception {
        User userData = getUser(userId);
        userData.setEmail(user.getEmail());
        userData.setName(user.getName());
        iUserRepository.save(userData);
        return user;
    }


    @Override
    public String deleteUser(UUID userId) {
        iUserRepository.deleteById(userId);
        return "User removed successfully";
    }

}
