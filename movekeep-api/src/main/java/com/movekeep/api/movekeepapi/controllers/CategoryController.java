package com.movekeep.api.movekeepapi.controllers;

import com.movekeep.api.movekeepapi.model.entity.Category;
import com.movekeep.api.movekeepapi.model.repomanager.CategoryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class CategoryController {

    @Autowired
    private CategoryManager categoryManager;

    @RequestMapping(value = "/getCategories", method = RequestMethod.GET)
    public List<Category> categories() {
        return this.categoryManager.getJustTitle();
    }

    @RequestMapping(value = "/getCategoriesWithId", method = RequestMethod.GET)
    public List<Category> getCategories() {
        return this.categoryManager.findAll();
    }

    @RequestMapping(value = "/addCategory", method = RequestMethod.POST)
    public ResponseEntity addCategory(@RequestBody Category category) {

        this.categoryManager.save(category);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/getRoutineCategories/{routineId}", method = RequestMethod.GET)
    public List<Category> getFromRoutineId(@PathVariable Integer routineId) {

        return this.categoryManager.getRoutineCategories(routineId);
    }

}
