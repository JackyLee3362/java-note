package edu.note.java.clazz;

/**
 * 内部类的静态成员测试
 */
class Car {

    static class Engine {

        public static int version;
    }

    public static void main(String[] args) {
        int version = Engine.version;
        System.out.println(version);
    }
}