package com.movekeep.api.movekeepapi.model.repomanager;

import com.movekeep.api.movekeepapi.model.entity.Routine;
import com.movekeep.api.movekeepapi.model.entity.User;
import com.movekeep.api.movekeepapi.model.repository.RoutineRepo;
import com.movekeep.api.movekeepapi.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoutineManager {

    private RoutineRepo routineRepo;

    private UserManager userManager;

    private Parser<Routine> routineParser;

    @Autowired
    @Qualifier("RoutineParser")
    public void setRoutineParser(Parser<Routine> routineParser) {
        this.routineParser = routineParser;
    }

    @Autowired
    public void setRoutineRepo(RoutineRepo routineRepo) {
        this.routineRepo = routineRepo;
    }

    @Autowired
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

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

            Routine userRoutine = this.routineRepo.findRoutineByIdAndUser(routineId, this.userManager.findByUserName(userName));

            this.routineRepo.delete(userRoutine);

            return true;

        } catch (Exception e) {

            return false;
        }
    }

    public Routine getConcreteRoutine(Integer routineId, String userName) {

        User routineOwner = this.userManager.findByUserName(userName);

        routineOwner.setCreationDate(null);
        routineOwner.setUserName(null);

        return this.routineParser.parse(this.routineRepo.findRoutineByIdAndUser(routineId, routineOwner));
    }
}
