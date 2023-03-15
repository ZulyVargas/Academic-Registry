package com.perficient.courseregistry.app.repository;

import com.perficient.courseregistry.app.entities.User;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface IUserRepository extends CrudRepository<User, String> {
    
    @Modifying
    @Query("UPDATE Users SET active_user= false WHERE user_id= :id ")
    boolean updateActive(@Param("id") UUID userId);
}