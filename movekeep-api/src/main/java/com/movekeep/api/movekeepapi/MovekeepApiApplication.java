package com.movekeep.api.movekeepapi;

import com.movekeep.api.movekeepapi.model.entity.*;
import com.movekeep.api.movekeepapi.model.repomanager.CategoryManager;
import com.movekeep.api.movekeepapi.model.repomanager.RoutineManager;
import com.movekeep.api.movekeepapi.model.repomanager.UserManager;
import com.movekeep.api.movekeepapi.model.repository.ExerciseRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class MovekeepApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovekeepApiApplication.class, args);
	}

	@Bean
    CommandLineRunner commandLineRunner(RoutineManager routineManager, UserManager userManager, CategoryManager categoryManager) {
		return (args) -> {
		    //List<Routine> routines = routineManager.getRoutines();

		    User user = new User();
		    user.setCreationDate(new Date());
		    user.setName("Joan Piza Ferra");
		    user.setUserName("jpizaf");

		    userManager.createUser(user);

            Exercise excercise = new Exercise();
            excercise.setAmount(20);
            excercise.setDescription("Sentadillas");

            Exercise exercise2 = new Exercise();
            exercise2.setAmount(10);
            exercise2.setDescription("A tope bro");

            ArrayList<Exercise> exercises = new ArrayList<>();
            exercises.add(excercise);
            exercises.add(exercise2);

            Category c1 = new Category();
            c1.setTitle("Braços");
            Category c2 = new Category();
            c2.setTitle("Cames");

            categoryManager.save(c1);
            categoryManager.save(c2);

            List<Category> categories = new ArrayList<>();
            categories.add(c1);
            categories.add(c2);

            Routine routine = new Routine();
            routine.setTitle("Titol amazing");
            routine.setType(TypeRoutine.reps);
            routine.setExercises(exercises);
            routine.setUser(user);
            routine.setCategories(categories);
            //categories.forEach(category -> routine.getCategories().add(category));

            //exerciceRepo.save(e);

            routineManager.save(routine);
            //exerciceRepo.save(e);
		};
	}
}
