package com.perficient.courseregistry.app.repository;

import com.perficient.courseregistry.app.entities.User;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface IUserRepository extends CrudRepository<User, String> {

    @Query("SELECT * FROM USERS WHERE active")
    Set<User> findAll();

    @Query("SELECT * FROM USERS WHERE active LIMIT :limit OFFSET :offset")
    Set<User>findAllPageable(@Param("limit") Integer limit,@Param("offset") Integer offset );

    Optional<User> findByUsername(String username);

    @Modifying
    @Query("UPDATE Users SET active= false WHERE user_id= :id ")
    boolean updateActive(@Param("id") UUID id);
}