package com.movekeep.api.movekeepapi.model.repomanager;

import com.movekeep.api.movekeepapi.model.entity.Comment;
import com.movekeep.api.movekeepapi.model.entity.Routine;
import com.movekeep.api.movekeepapi.model.repository.CommentRepo;
import com.movekeep.api.movekeepapi.parser.CommentParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentManager {

    private CommentRepo commentRepo;

    @Autowired
    public void setCommentRepo(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    public void saveComment(Comment comment){

        comment.setDate(new Date());

        this.commentRepo.save(comment);
    }

    public List<Comment> getRoutineComments(Comment comment) {

        if (null != comment.getContent() && !"".equals(comment.getContent())) this.saveComment(comment);

        return this.commentRepo.findAllByRoutine_Id(comment.getRoutine().getId())
                .stream()
                .map(CommentParser::parseComment)
                .collect(Collectors.toList());
    }

}
