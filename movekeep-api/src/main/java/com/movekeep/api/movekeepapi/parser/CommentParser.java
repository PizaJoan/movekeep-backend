package com.movekeep.api.movekeepapi.parser;

import com.movekeep.api.movekeepapi.model.entity.Comment;
import org.springframework.stereotype.Component;

@Component("CommentParser")
public class CommentParser implements Parser<Comment> {

    @Override
    public Comment parse(Comment comment) {

        comment.setRoutine(null);
        comment.getUser().setCreationDate(null);
        comment.getUser().setPathToImage(null);
        comment.getUser().setUserName(null);
        return comment;
    }
}
