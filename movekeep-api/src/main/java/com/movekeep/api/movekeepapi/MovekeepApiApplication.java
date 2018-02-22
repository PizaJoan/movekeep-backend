package com.movekeep.api.movekeepapi;

import com.movekeep.api.movekeepapi.model.entity.Exercise;
import com.movekeep.api.movekeepapi.model.entity.Routine;
import com.movekeep.api.movekeepapi.model.entity.TypeRoutine;
import com.movekeep.api.movekeepapi.model.entity.User;
import com.movekeep.api.movekeepapi.model.repomanager.RoutineManager;
import com.movekeep.api.movekeepapi.model.repomanager.UserManager;
import com.movekeep.api.movekeepapi.model.repository.ExerciseRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class MovekeepApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovekeepApiApplication.class, args);
	}

	@Bean
    CommandLineRunner commandLineRunner(RoutineManager routineManager, UserManager userManager, ExerciseRepo exerciceRepo) {
		return (args) -> {
		    User user = userManager.findByUserName("Joan Piza Ferra");
            Exercise e = new Exercise();
            e.setAmount(20);
            e.setDescription("Sentadillas");

            ArrayList<Exercise> exercises = new ArrayList<>();
            exercises.add(e);

            Routine routine = new Routine();
            routine.setTitle("Titol amazing");
            routine.setType(TypeRoutine.reps);
            routine.setExercises(exercises);
            routine.setUser(user);

            routineManager.save(routine);
            //exerciceRepo.save(e);
		};
	}
}
