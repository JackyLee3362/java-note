package edu.note.java.syntax;

public class TestEnum {
    public static void main(String[] args) {
        System.out.println(Color.ONE);
    }
}

enum Color{
    ONE;
    Color() {
        System.out.println("init");
    }
    static {
        System.out.println("static");
    }
}