package com.example.cst438_project02;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    @Query("select u from User u where upper(u.username) like upper(?1)")
    User findByUsernameLikeIgnoreCase(String username);

    boolean existsByUsernameLikeIgnoreCase(String username);


}
