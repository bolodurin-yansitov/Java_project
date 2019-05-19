package com.example.prototipe.daos;


import com.example.prototipe.entities.HeadmansRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HeadmansRequestDao
        extends CrudRepository<HeadmansRequest, Long> {

    @Query("SELECT headman_request FROM HeadmansRequest headman_request " +
            "WHERE headman_request.headmanId = :headmanId")
    HeadmansRequest findHeadmansRequestByHeadmanId(@Param("headmanId") Long headmanId);


}
