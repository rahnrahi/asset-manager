package org.rahi.aseet.repositories;

import org.rahi.aseet.Entities.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserAccountEntity, UUID> {

    Optional<UserAccountEntity> findByEmail(String email);

    Boolean existsByEmail(String email);

    @Query(
            "SELECT e FROM Users e " +
                    "WHERE lower(e.email) LIKE lower(concat('%', :email, '%')) " +
                    "AND e.password = :password "
    )
    Optional<UserAccountEntity> loginUser(@Param("email") String email, @Param("password") String password);
}
