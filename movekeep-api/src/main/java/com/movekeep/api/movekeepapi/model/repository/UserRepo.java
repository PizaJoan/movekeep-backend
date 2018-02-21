package com.movekeep.api.movekeepapi.model.repository;

import com.movekeep.api.movekeepapi.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {

    User findByUserName(String userName);
}
