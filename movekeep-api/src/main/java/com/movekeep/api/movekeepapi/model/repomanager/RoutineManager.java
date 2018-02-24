package com.movekeep.api.movekeepapi.model.repomanager;

import com.movekeep.api.movekeepapi.model.entity.Routine;
import com.movekeep.api.movekeepapi.model.entity.User;
import com.movekeep.api.movekeepapi.model.repository.RoutineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoutineManager {

    @Autowired
    private RoutineRepo routineRepo;

    public List<Routine> getRoutines() {
        return (List<Routine>) this.routineRepo.findAll();
    }

    public void save(Routine routine) {
        this.routineRepo.save(routine);
    }

    public List<Routine> getRoutineCategoryTitle(String title) {
        return this.routineRepo.findAllByCategoryTitle(title);
    }

    public List<Routine> getMyRoutines(String userName) {
        return this.routineRepo.findAllByUserName(userName);
    }

    public Long countComments(Integer routineId) {
        return this.routineRepo.countByComments(routineId);
    }

    public void removeRoutine(Integer routineId) {
        this.routineRepo.delete(routineId);
    }
}
