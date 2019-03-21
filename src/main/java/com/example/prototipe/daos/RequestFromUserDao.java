package com.example.prototipe.daos;

import com.example.prototipe.entities.RequestFromUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestFromUserDao
        extends CrudRepository<RequestFromUser, Long> {
}
