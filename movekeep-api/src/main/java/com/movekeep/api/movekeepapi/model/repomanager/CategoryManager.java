package com.movekeep.api.movekeepapi.model.repomanager;

import com.movekeep.api.movekeepapi.model.entity.Category;
import com.movekeep.api.movekeepapi.model.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryManager {

    private CategoryRepo categoryRepo;

    @Autowired
    public void setCategoryRepo(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public void save(Category category) {
        this.categoryRepo.save(category);
    }

    public List<Category> getJustTitle() {
        return this.categoryRepo.findJustCategories();
    }

    public List<Category> findAll() {
        return (List<Category>) this.categoryRepo.findAll();
    }

    public List<Category> getRoutineCategories(Integer id) {
        return this.categoryRepo.findAllByRoutineId(id);
    }
}
