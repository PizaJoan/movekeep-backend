package com.movekeep.api.movekeepapi.model.repomanager;

import com.movekeep.api.movekeepapi.model.entity.Comment;
import com.movekeep.api.movekeepapi.model.repository.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentManager {

    @Autowired
    private CommentRepo commentRepo;

    public void saveComment(Comment comment){
        this.commentRepo.save(comment);
    }

}
