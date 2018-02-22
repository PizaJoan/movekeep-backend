package com.movekeep.api.movekeepapi.controllers;

import com.movekeep.api.movekeepapi.model.entity.Category;
import com.movekeep.api.movekeepapi.model.repomanager.CategoryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryManager categoryManager;

    @RequestMapping(value = "/getCategories", method = RequestMethod.GET)
    public List<String> categories() {
        return this.categoryManager.getJustTitle();
    }

    @RequestMapping(value = "/addCategory", method = RequestMethod.PUT)
    public ResponseEntity addCategory(@RequestBody Category category) {

        this.categoryManager.save(category);

        return new ResponseEntity(HttpStatus.OK);
    }

}
