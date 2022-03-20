package com.example.cst438_project02;

import org.springframework.data.repository.CrudRepository;

public interface WishListRepository extends CrudRepository <WishList,Integer> {
    WishList findByNameLike(String name);

    boolean existsByNameLikeIgnoreCase(String name);



}
