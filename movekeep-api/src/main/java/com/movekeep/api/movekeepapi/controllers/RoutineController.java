package com.movekeep.api.movekeepapi.controllers;

import com.movekeep.api.movekeepapi.model.entity.Routine;
import com.movekeep.api.movekeepapi.model.repomanager.RoutineManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoutineController {

    @Autowired
    private RoutineManager routineManager;

    @RequestMapping(value = "/getRoutines", method = RequestMethod.GET)
    public List<Routine> getRoutines() {

        return this.routineManager.getRoutines();
    }


    @RequestMapping(value = "/getRoutinesByCategory/{title}", method = RequestMethod.GET)
    public List<Routine> getRoutinesFromCategory(@PathVariable String title) {

        return this.routineManager.getRoutineCategoryTitle(title);
    }

    @RequestMapping(value = "/getMyRoutines/{userName}", method = RequestMethod.GET)
    public List<Routine> getMyRoutines(@PathVariable String userName) {

        return this.routineManager.getMyRoutines(userName);

    }

    @RequestMapping(value = "/countComments/{routineId}", method = RequestMethod.GET)
    public Long getRoutineCommentCount(@PathVariable Integer routineId) {

        return this.routineManager.countComments(routineId);
    }
}
