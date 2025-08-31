package edu.note.java.syntax;

public enum Color {
    RED, GREEN, BLUE;

    Color() {
        System.out.println("init");
    }

    static {
        System.out.println("static");
    }
}
