
package com.meta.smartbiz.repo;

import com.meta.smartbiz.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email); // for login lookup
}
