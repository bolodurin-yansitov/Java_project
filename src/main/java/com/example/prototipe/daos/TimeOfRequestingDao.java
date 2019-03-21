package com.example.prototipe.daos;

import com.example.prototipe.entities.TimeOfRequesting;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface TimeOfRequestingDao
        extends CrudRepository<TimeOfRequesting, Date> {
}
