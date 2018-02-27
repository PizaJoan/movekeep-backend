package com.movekeep.api.movekeepapi.model.repository;

import com.movekeep.api.movekeepapi.model.entity.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends CrudRepository<Category, Integer> {

    @Query(value = "SELECT new Category(c.title) FROM Category AS c")
    List<Category> findJustCategories();

    @Query(value = "SELECT new Category(c.title) FROM Category AS c " +
            "JOIN c.routines AS r " +
            "WHERE r.id = ?1")
    List<Category> findAllByRoutineId(Integer routineId);

}
