package com.w4.projetoIntegrador.repository;

import com.w4.projetoIntegrador.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
    User findByEmail(String email);


    @Query(value = "select username, email, enabled, profile_type as profileType from users where email = ?1 " , nativeQuery = true)
    EmailUsers findUserByEmailQuery(String mail);

    public interface EmailUsers {
        String getUsername();
        String getEmail();
        Boolean getEnabled();
        String getProfileType();
    }
}
