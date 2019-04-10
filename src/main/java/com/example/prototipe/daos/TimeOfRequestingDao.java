package com.example.prototipe.daos;

import com.example.prototipe.entities.TimeOfRequesting;
import com.example.prototipe.entities.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface TimeOfRequestingDao
        extends CrudRepository<TimeOfRequesting, Date> {
    @Query("SELECT time_of_requesting FROM TimeOfRequesting time_of_requesting " +
            "WHERE time_of_requesting.headmanId = :headmanId")
    TimeOfRequesting findTimeOfRequestingByHeadmanId(
            @Param("headmanId") Long headmanId);

    @Query("SELECT headman FROM Users headman WHERE headman.role = 2 OR " +
            "headman.role = 3 AND " +
            "headman.courseNum = (SELECT user.courseNum FROM Users user " +
            "WHERE user.id = :userId)")
    Users findHeadmanOfUserByUserId(@Param("userId") Long userId);
}
