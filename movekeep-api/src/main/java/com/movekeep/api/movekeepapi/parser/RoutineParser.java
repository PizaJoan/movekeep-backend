package com.movekeep.api.movekeepapi.parser;

import com.movekeep.api.movekeepapi.model.entity.Comment;
import com.movekeep.api.movekeepapi.model.entity.Routine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component("RoutineParser")
public class RoutineParser implements Parser<Routine> {

    private Parser<Comment> commentParser;

    @Autowired
    @Qualifier("CommentParser")
    public void setCommentParser(Parser<Comment> commentParser) {
        this.commentParser = commentParser;
    }

    @Override
    public Routine parse(Routine routine) {

        routine.setComments(routine.getComments()
                .stream()
                .map(this.commentParser::parse)
                .collect(Collectors.toList()));
        return routine;
    }
}
