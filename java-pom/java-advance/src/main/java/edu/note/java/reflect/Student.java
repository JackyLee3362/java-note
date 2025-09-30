package edu.note.java.reflect;

// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Data;
// import lombok.NoArgsConstructor;
// import lombok.extern.slf4j.Slf4j;

import lombok.Getter;
import lombok.Setter;

// @AllArgsConstructor
// @NoArgsConstructor
// @Builder
public class Student {

    @Getter
    @Setter
    private String name;
    private int age;
    private double height;
    private char gender;

    public Student() {
    }

    private Student(int age, double height) {
        this.age = age;
        this.height = height;
    }

    Student(int age) {
        this.age = age;
    }

    protected Student(double height) {
        this.height = height;
    }

    public Student(String name) {
        this.name = name;
    }

    private Student(String name, int age, char gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public Student(String name, int age, double height, char gender) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.gender = gender;
    }

    public String eat(String food) throws Exception {
        return food;
    }

}
