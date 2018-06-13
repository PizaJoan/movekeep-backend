package com.movekeep.api.movekeepapi.model.repomanager;

import com.movekeep.api.movekeepapi.model.entity.Comment;
import com.movekeep.api.movekeepapi.model.repository.CommentRepo;
import com.movekeep.api.movekeepapi.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CommentManager {

    private CommentRepo commentRepo;

    private Parser<Comment> commentParser;

    @Autowired
    @Qualifier("CommentParser")
    public void setCommentParser(Parser<Comment> parser) {
        this.commentParser = parser;
    }

    @Autowired
    public void setCommentRepo(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    public void saveComment(Comment comment){

        comment.setDate(new Date());

        this.commentRepo.save(comment);
    }

    public Comment getRoutineComments(Comment comment) {

        if (null != comment.getContent() && !"".equals(comment.getContent())) this.saveComment(comment);

        return this.commentParser.parse(this.commentRepo.findOne(comment.getId()));
    }

}
