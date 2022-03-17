package com.example.cst438_project02;

import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Items, Integer> {
    Items findByNameLikeIgnoreCase(String name);

    boolean existsByNameLikeIgnoreCase(String name);



}
