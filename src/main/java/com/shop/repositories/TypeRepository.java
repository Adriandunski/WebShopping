package com.shop.repositories;

import com.shop.models.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TypeRepository extends JpaRepository<Type, Long> {

    @Query("SELECT t from Type t where t.typeName = ?1")
    Type findTypeByName(String typeName);
}
