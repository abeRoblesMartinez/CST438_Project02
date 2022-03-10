package com.example.cst438_project02;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    boolean existsByUsernameIgnoreCase(String username);

    @Query(value ="SELECT * User u WHERE u.name like %:name%",
    countQuery ="SELECT count(*) from User",
    nativeQuery = true)
    List<User> findUserByName(
            @Param("name") String name
    );

    @Transactional
    @Modifying
    @Query("update User u set u.password = ?1")
    int setPassword(String password);






}
