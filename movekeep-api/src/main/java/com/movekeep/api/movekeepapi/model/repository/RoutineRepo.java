package com.movekeep.api.movekeepapi.model.repository;

import com.movekeep.api.movekeepapi.model.entity.Category;
import com.movekeep.api.movekeepapi.model.entity.Routine;
import com.movekeep.api.movekeepapi.model.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoutineRepo extends CrudRepository<Routine, Integer> {

    @Query(value = "SELECT new Routine(r.title, r.description, r.type, r.user, r.creationDate) FROM Routine AS r " +
            "JOIN r.categories AS c " +
            "JOIN r.user u " +
            "WHERE c.title = ?1")
    List<Routine> findAllByCategoryTitle(String title);


    @Query(value = "SELECT new Routine(r.id, r.title, r.description, r.type, r.creationDate) FROM Routine r " +
            "JOIN r.user AS u " +
            "WHERE u.userName = ?1")
    List<Routine> findAllByUserName(String userName);

    @Query(value = "SELECT count(*) FROM Comment c " +
            "WHERE routine_id = ?1", nativeQuery = true)
    Long countByComments(Integer id);

    Routine findRoutineByIdAndUser(Integer id, User user);
/*
    @Query(value = "SELECT new Routine(r.title, r.description, r.type, r.exercises) FROM Routine AS r " +
            "JOIN r.exercises AS e " +
            "JOIN r.user AS u " +
            "WHERE r.id = ?1 AND u.userName = ?2")
    Routine concreteRoutineBydIdAndUser(Integer id, String userName);*/
}
