package org.rahi.aseet.repositories;

import org.rahi.aseet.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IUserRepository extends JpaRepository<Users, UUID> {

    Optional<Users> findByEmail(String email);

    Boolean existsByEmail(String email);

    @Query(
            "SELECT e FROM AssetUser e " +
                    "WHERE lower(e.email) LIKE lower(concat('%', :email, '%')) " +
                    "AND e.password = :password "
    )
    Optional<Users> loginUser(@Param("email") String email, @Param("password") String password);
}
