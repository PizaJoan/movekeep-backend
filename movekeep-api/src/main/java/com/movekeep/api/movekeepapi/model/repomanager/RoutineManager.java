package com.movekeep.api.movekeepapi.model.repomanager;

import com.movekeep.api.movekeepapi.model.entity.Routine;
import com.movekeep.api.movekeepapi.model.entity.User;
import com.movekeep.api.movekeepapi.model.repository.RoutineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Component
public class RoutineManager {

    @Autowired
    private RoutineRepo routineRepo;

    @Autowired
    private UserManager userManager;

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

    public boolean removeRoutine(Integer routineId, String userName) {
        try {
            User routineOwner = this.userManager.findByUserName(userName);

            Routine userRoutine = this.routineRepo.findRoutineByIdAndUser(routineId, routineOwner);

            this.routineRepo.delete(userRoutine);

            return true;

        } catch (Exception e) {

            return false;
        }
    }
}
