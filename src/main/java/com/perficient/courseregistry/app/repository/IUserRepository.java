package com.perficient.courseregistry.app.repository;

import com.perficient.courseregistry.app.entities.User;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface IUserRepository extends CrudRepository<User, String> {

    @Query("SELECT * FROM USERS WHERE active OR active=:isActive LIMIT :limit OFFSET :offset")
    Set<User>findAll(@Param("limit") Integer limit,@Param("offset") Integer offset, @Param("isActive") boolean isActive );

    Optional<User> findByUsername(String username);

    @Modifying
    @Query("UPDATE Users SET active= false WHERE user_id= :id ")
    boolean updateActive(@Param("id") UUID userId);
}