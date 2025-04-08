package com.workintech.s17d2.model;

public class JuniorDeveloper extends Developer {

    public JuniorDeveloper(int id, String name, double salary) {
        super(id, name, salary - (salary * 10 / 100), Experience.JUNIOR);
    }
}
