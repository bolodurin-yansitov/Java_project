package com.example.prototipe.daos;

import com.example.prototipe.entities.Key;
import com.example.prototipe.entities.RequestFromUser;
import com.example.prototipe.entities.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestFromUserDao
        extends CrudRepository<RequestFromUser, Long> {

    @Query("SELECT user FROM Users user WHERE user.id = :userId")
    Users findUserByUsersId(@Param("userId") Long userId);

    @Query("SELECT key_ FROM Key key_ WHERE key_.userId = :userId")
    List<Key> findKeysByUserId(@Param("userId") Long userId);

    @Query("SELECT headman FROM Users headman WHERE headman.role = 2 OR " +
            "headman.role = 3 AND " +
            "headman.courseNum = (SELECT user.courseNum FROM Users user " +
            "WHERE user.id = :userId) AND headman.faculNum = (SELECT " +
            "user.faculNum FROM Users user WHERE user.id = :userId)")
    Users findHeadmanByUserId(@Param("userId") Long userId);
}
