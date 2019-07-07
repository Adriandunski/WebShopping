package com.shop.repositories;

import com.shop.models.Type;
import org.hibernate.type.ListType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeaturesRepository extends JpaRepository<Type, Long> {

    @Query("SELECT t from Type t WHERE t.typeName = ?1")
    Type getFeaturesOfTypeByName(String type);
}
