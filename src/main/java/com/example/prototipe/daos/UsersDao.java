package com.example.prototipe.daos;

import com.example.prototipe.entities.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDao extends CrudRepository<Users, Long> {
    @Query("SELECT user FROM Users user WHERE user.email = :email")
    Users findByEmail(@Param("email") String email);
}
