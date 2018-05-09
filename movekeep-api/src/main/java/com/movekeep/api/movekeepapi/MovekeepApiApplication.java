package com.movekeep.api.movekeepapi;

import com.movekeep.api.movekeepapi.model.entity.*;
import com.movekeep.api.movekeepapi.model.repomanager.CategoryManager;
import com.movekeep.api.movekeepapi.model.repomanager.CommentManager;
import com.movekeep.api.movekeepapi.model.repomanager.RoutineManager;
import com.movekeep.api.movekeepapi.model.repomanager.UserManager;
import com.movekeep.api.movekeepapi.model.repository.ExerciseRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories
public class MovekeepApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovekeepApiApplication.class, args);
	}

	@Bean
    CommandLineRunner commandLineRunner(RoutineManager routineManager, UserManager userManager, CategoryManager categoryManager, CommentManager commentManager) {
		return (args) -> {
		    //List<Routine> routines = routineManager.getRoutines();

		    User user = new User();
		    user.setCreationDate(new Date());
		    user.setName("Joan Piza Ferra");
		    user.setUserName("jpizaf");

		    userManager.createUser(user);

            User user2 = new User();
            user2.setCreationDate(new Date());
            user2.setName("Joan Piza");
            user2.setUserName("jpizaf1");

            userManager.createUser(user2);

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
            routine.setCreationDate(new Date());
            //categories.forEach(category -> routine.getCategories().add(category));

            Routine routine2 = new Routine();
            routine2.setTitle("Titol amazing2");
            routine2.setType(TypeRoutine.time);
            routine2.setUser(user2);
            routine2.setCategories(categories);
            routine2.setCreationDate(new Date());

            Exercise e1 = new Exercise();
            e1.setAmount(20);
            e1.setDescription("jajajlol");
            Exercise e2 = new Exercise();
            e2.setAmount(20);
            e2.setDescription("jajajlol");
            List<Exercise> e = new ArrayList<>();
            e.add(e1);
            e.add(e2);
            routine2.setExercises(e);
            //exerciceRepo.save(e);

            routineManager.save(routine);
            routineManager.save(routine2);

            Comment comment = new Comment();
            comment.setContent("Això és un comentari de prova");
            comment.setDate(new Date());
            comment.setRoutine(routine2);
            comment.setUser(user2);

            Comment comment2 = new Comment();
            comment2.setContent("Ajajajajajajajaj");
            comment2.setDate(new Date());
            comment2.setUser(user2);
            comment2.setRoutine(routine2);

            commentManager.saveComment(comment);
            commentManager.saveComment(comment2);


            routine2.getExercises().remove(1);

            routineManager.save(routine2);


            //routineManager.removeRoutine(routine2.getId());

            //exerciceRepo.save(e);
		};
	}
}
