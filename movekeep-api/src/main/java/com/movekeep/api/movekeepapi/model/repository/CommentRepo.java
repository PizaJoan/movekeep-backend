package com.movekeep.api.movekeepapi.model.repository;

import com.movekeep.api.movekeepapi.model.entity.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends CrudRepository<Comment, Integer> {
}
