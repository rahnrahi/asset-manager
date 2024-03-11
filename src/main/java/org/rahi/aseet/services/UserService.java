package org.rahi.aseet.services;

import org.rahi.aseet.Entities.RoleEntity;
import org.rahi.aseet.Entities.UserAccountEntity;
import org.rahi.aseet.Entities.types.RoleTypes;
import org.rahi.aseet.payload.request.SignUpRequest;
import org.rahi.aseet.repositories.RoleRepository;
import org.rahi.aseet.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService implements IUsersService, UserDetailsService {

    @Autowired
    private UserRepository iUserRepository;

    private RoleRepository roleRepository;

    @Override
    public List<UserAccountEntity> getUser() {
        return iUserRepository.findAll();
    }

    @Override
    public UserAccountEntity saveUser(SignUpRequest signUpRequest) {
        Set<String> strRoles = signUpRequest.getRole();
        Set<RoleEntity> roles = new HashSet<>();
        if (strRoles == null) {
            RoleEntity userRole = roleRepository.findByName(RoleTypes.ROLE_CUSTOMER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        RoleEntity adminRole = roleRepository.findByName(RoleTypes.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        RoleEntity modRole = roleRepository.findByName(RoleTypes.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        RoleEntity userRole = roleRepository.findByName(RoleTypes.ROLE_CUSTOMER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        UserAccountEntity user = new UserAccountEntity();

        user.setPassword(signUpRequest.getPassword());
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setRoles(roles);

        return iUserRepository.save(user);
    }

    @Override
    public UserAccountEntity getUser(UUID userId) throws Exception {
        var userDetails = iUserRepository.findById(userId);
        if(userDetails.isEmpty()){
            throw new Exception("User not found");
        }
        return userDetails.get();
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserAccountEntity user = iUserRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }

    @Override
    public UserAccountEntity updateUser(UUID userId, UserAccountEntity user) throws Exception {
        UserAccountEntity userData = getUser(userId);
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
