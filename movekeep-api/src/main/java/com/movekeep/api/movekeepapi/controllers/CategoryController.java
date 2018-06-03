package com.movekeep.api.movekeepapi.controllers;

import com.movekeep.api.movekeepapi.model.entity.Category;
import com.movekeep.api.movekeepapi.model.repomanager.CategoryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryManager categoryManager;

    @Autowired
    public void setCategoryManager(CategoryManager categoryManager) {
        this.categoryManager = categoryManager;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Category> categories() {

        return this.categoryManager.getJustTitle();
    }

    @RequestMapping(value = "/all/id", method = RequestMethod.GET)
    public List<Category> getCategories() {
        return this.categoryManager.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity addCategory(@RequestBody Category category) {

        this.categoryManager.save(category);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/categories/routine/{routineId}", method = RequestMethod.GET)
    public List<Category> getFromRoutineId(@PathVariable Integer routineId) {

        return this.categoryManager.getRoutineCategories(routineId);
    }
}
