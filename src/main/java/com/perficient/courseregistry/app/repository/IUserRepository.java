package com.perficient.courseregistry.app.repository;

import com.perficient.courseregistry.app.entities.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends CrudRepository<User, String> {

    @Query("SELECT * FROM USERS LIMIT :limit OFFSET :offset")
    List<User>findAllPageable(@Param("limit") Integer limit,@Param("offset") Integer offset );

    Optional<User> findByUsername(String username);
}