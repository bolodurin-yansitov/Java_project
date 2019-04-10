package com.example.prototipe.daos;

import com.example.prototipe.entities.Key;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyDao extends CrudRepository<Key, Long> {
    @Query("SELECT key_ FROM Key key_ WHERE key_.openKey = :open_key")
    Key findByOpenKey(@Param("open_key") Long openKey);

    @Query("SELECT key_ FROM Key key_ WHERE key_.userId = :userId")
    Key findByUserId(@Param("userId") Long userId);
}
