package com.perficient.courseregistry.app.repository;

import com.perficient.courseregistry.app.entities.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRespository extends CrudRepository<User,String> {
    //Auth
    @Query("SELECT * from USERS WHERE username= :username AND password = :password")
    User findByCredentials(@Param("username") String username, @Param("password") String password);

}
