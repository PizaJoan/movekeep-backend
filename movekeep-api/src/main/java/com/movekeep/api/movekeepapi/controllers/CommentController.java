package com.movekeep.api.movekeepapi.controllers;

import com.movekeep.api.movekeepapi.model.entity.Comment;
import com.movekeep.api.movekeepapi.model.repomanager.CommentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CommentController {

    private CommentManager commentManager;

    @Autowired
    public void setCommentManager(CommentManager commentManager) {
        this.commentManager = commentManager;
    }

    @MessageMapping("/insert")
    @SendTo("/get-comments/get")
    public Comment getRoutineComments(@RequestBody Comment comment) {

        return this.commentManager.getRoutineComments(comment);
    }

}
