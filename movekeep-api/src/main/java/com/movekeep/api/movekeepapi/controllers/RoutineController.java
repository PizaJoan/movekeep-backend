package com.movekeep.api.movekeepapi.controllers;

import com.movekeep.api.movekeepapi.model.entity.Routine;
import com.movekeep.api.movekeepapi.model.repomanager.RoutineManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoutineController {

    @Autowired
    private RoutineManager routineManager;

    @RequestMapping(value = "/getRoutines", method = RequestMethod.GET)
    public List<Routine> getRoutines() {
        return this.routineManager.getRoutines();
    }
}
