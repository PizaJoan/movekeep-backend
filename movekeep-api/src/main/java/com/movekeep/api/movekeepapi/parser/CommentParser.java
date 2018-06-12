package com.movekeep.api.movekeepapi.parser;

import com.movekeep.api.movekeepapi.model.entity.Comment;

public class CommentParser {

    public static Comment parseComment(Comment comment) {

        comment.setRoutine(null);
        comment.getUser().setCreationDate(null);
        comment.getUser().setPathToImage(null);
        comment.getUser().setUserName(null);
        return comment;
    }
}
