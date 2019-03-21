package com.example.prototipe.daos;


import com.example.prototipe.entities.HeadmansRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeadmansRequestDao
        extends CrudRepository<HeadmansRequest, Long> {
}
