package com.sonnguyen.base.repository;

import com.sonnguyen.base.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUsername(String username);
//    exists: return boolean

    User findByUsername(String username);
//    findByUsername: return User or Optional<User>
}
