package com.mgbt.turismoargentina_backend.model.repository;

import com.mgbt.turismoargentina_backend.model.entity.User;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query(value = "SELECT COUNT(*) FROM turismo_argentina.users u " +
            "WHERE u.deletion_date IS NULL", nativeQuery = true)
    Long findCount();
}
