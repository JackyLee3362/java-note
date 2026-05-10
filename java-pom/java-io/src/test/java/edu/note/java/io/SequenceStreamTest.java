package edu.note.java.io;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.Data;

/**
 * @author jackylee
 * @date 2024/12/1 上午12:01
 */
// 利用序列化流/对象操作输出流，把一个对象写到本地文件中
public class SequenceStreamTest extends BaseIOTest {

    File read = new File(ioDir, "sequence.read.txt");
    File write = new File(ioDir, "sequence.write.txt");

    @Test
    @DisplayName("序列化对象")
    void test01() throws IOException {

        // public ObjectOutputStream(OutputStream out) 把基本流变成高级流
        // public final void writeObject(Object obj) 把对象序列化（写出）到文件中去

        // 1.创建对象
        Student stu = new Student("John", 23);
        // 2.创建序列化流的对象/对象操作输出流
        ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(read.toPath()));
        // 3. 写出数据
        oos.writeObject(stu);
        // 4. 释放资源
        oos.close();
    }

    @Test
    @DisplayName("反序列化 对象操作输入流")
    void test02() throws IOException, ClassNotFoundException {

        // public ObjectInputStream(InputStream out) 把基本流变成高级流
        // public Object readObject() 把序列化到本地文件中的对象，读取到程序中来

        // 1.创建反序列化流的对象
        ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(read.toPath()));

        // 2.读取数据
        Student o = (Student) ois.readObject();

        // 3.打印对象
        System.out.println(o);

        // 4.释放资源
        ois.close();

    }

    @Test
    @DisplayName("列表 序列化")
    void test03() throws IOException {
        // 将多个自定义对象序列化到文件中，但是对象的个数不确定，该如何操作呢？
        // 1.序列化多个对象
        Student s1 = new Student("Jack", 23, "南京");
        Student s2 = new Student("John", 24, "重庆");
        Student s3 = new Student("Mick", 25, "北京");

        List<Student> list = new ArrayList<>();
        list.add(s1);
        list.add(s2);
        list.add(s3);
        ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(write.toPath()));
        oos.writeObject(list);
        oos.close();
    }

    @Test
    @DisplayName("列表 反序列化")
    void test04() throws IOException, ClassNotFoundException {
        // 将多个自定义对象序列化到文件中，但是对象的个数不确定，该如何操作呢？
        // 1.序列化多个对象
        ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(write.toPath()));
        List<Student> o = (List<Student>) ois.readObject();
        System.out.println(o);
    }

    /*
     *
     * Serializable接口里面是没有抽象方法，标记型接口
     * 一旦实现了这个接口，那么就表示当前的Student类可以被序列化
     * 理解：
     * 一个物品的合格证
     */
    @Data
    public class Student implements Serializable {

        // @Serial
        private static final long serialVersionUID = 2252015747540652000L;
        private String name;
        private int age;

        // transient 瞬态关键字
        // 不会把当前属性序列化到本地文件当中
        private transient String address;

        public Student() {
        }

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public Student(String name, int age, String address) {
            this.name = name;
            this.age = age;
            this.address = address;
        }
    }

}
