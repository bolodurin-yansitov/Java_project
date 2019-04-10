package com.example.prototipe.daos;

import com.example.prototipe.entities.RequestFromUser;
import com.example.prototipe.entities.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestFromUserDao
        extends CrudRepository<RequestFromUser, Long> {

    @Query("SELECT user FROM Users user WHERE user.id = :userId")
    Users findUserByUsersId(@Param("userId") Long userId);
}
