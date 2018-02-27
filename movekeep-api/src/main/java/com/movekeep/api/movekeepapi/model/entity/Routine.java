package com.movekeep.api.movekeepapi.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Routine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "title", nullable = false, columnDefinition = "varchar(40)")
    private String title;

    @Column(name = "description", columnDefinition = "varchar(300)")
    private String description;

    @Column(name = "type", nullable = false, columnDefinition = "enum('time', 'reps')")
    private String type;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    @JoinColumn(name = "routine_id", referencedColumnName = "id")
    private List<Exercise> exercises;

    @OneToOne(fetch = FetchType.EAGER)
    private User user;


    @ManyToMany
    @JoinTable(
            name = "routine_categories",
            joinColumns = { @JoinColumn(name = "routine_id") },
            inverseJoinColumns = { @JoinColumn(name = "category_id") }
    )
    private List<Category> categories;

    @OneToMany(mappedBy = "routine", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @Temporal(TemporalType.DATE)
    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    public Routine(String title, String description, String type, User user, Date creationDate) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.user = user;
        this.creationDate = creationDate;
        this.user.setUserName(null);
        this.user.setCreationDate(null);
    }

    public Routine(Integer id, String title, String description, String type, Date creationDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.creationDate = creationDate;
    }

    public Routine() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(TypeRoutine type) {
        this.type = type.name();
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
/*
    public Long getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Long commentsCount) {
        this.commentsCount = commentsCount;
    }
/*
    public void addExercise(Exercise exercise) {
        exercise.setRoutine(this);
    }*/
}
