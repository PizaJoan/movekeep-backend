package com.movekeep.api.movekeepapi.controllers;

import com.movekeep.api.movekeepapi.model.entity.Routine;
import com.movekeep.api.movekeepapi.model.repomanager.RoutineManager;
import com.movekeep.api.movekeepapi.model.repomanager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/routine")
public class RoutineController {

    private RoutineManager routineManager;

    private UserManager userManager;

    @Autowired
    public void setUserManager (UserManager userManager) {
        this.userManager = userManager;
    }

    @Autowired
    public void setRoutineManager(RoutineManager routineManager) {
        this.routineManager = routineManager;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Routine> getRoutines() {

        return this.routineManager.getRoutines();
    }

    @RequestMapping(value = "/all/category/{title}", method = RequestMethod.GET)
    public List<Routine> getRoutinesFromCategory(@PathVariable String title) {

        return this.routineManager.getRoutineCategoryTitle(title);
    }

    @RequestMapping(value = "/user/{userName}", method = RequestMethod.GET)
    public List<Routine> getMyRoutines(@PathVariable String userName) {

        return this.routineManager.getMyRoutines(userName);

    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity deleteRoutine(@RequestParam("routine") Integer routine, @RequestParam("username") String userName) {

        if (!this.routineManager.removeRoutine(routine, userName)) return new ResponseEntity(HttpStatus.BAD_REQUEST);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/comments/count/{routineId}", method = RequestMethod.GET)
    public Long getRoutineCommentCount(@PathVariable Integer routineId) {

        return this.routineManager.countComments(routineId);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity addRoutine(@RequestBody Routine routineToAdd) {

        routineToAdd.setUser(this.userManager.findByUserName(routineToAdd.getUser().getUserName()));

        this.routineManager.save(routineToAdd);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Routine getRoutine(@RequestParam("routine") Integer routine, @RequestParam("username") String userName) {

        return this.routineManager.getConcreteRoutine(routine, userName);
    }
}
