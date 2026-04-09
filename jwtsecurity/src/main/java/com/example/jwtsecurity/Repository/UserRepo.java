package com.example.jwtsecurity.Repository;
import com.example.jwtsecurity.entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserAuth,Long> {
    Optional<UserAuth> findByUsername(String username);
}
