package com.perficient.courseregistry.app.repository;

import com.perficient.courseregistry.app.entities.Subject;
import com.perficient.courseregistry.app.entities.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IUserRepository extends CrudRepository<User, String> {

    @Query("SELECT * FROM USERS WHERE active " )
    List<User> findAll();

    @Query("SELECT * FROM USERS WHERE active LIMIT :limit OFFSET :offset")
    List<User>findAllPageable(@Param("limit") Integer limit,@Param("offset") Integer offset );

    @Query("SELECT * FROM USERS WHERE active AND username=:username")
    User findByUsername(@Param("username") String username);

    @Query("SELECT * FROM USERS WHERE active AND user_id=:userId")
    User findById(@Param("userId") UUID userId );


}
