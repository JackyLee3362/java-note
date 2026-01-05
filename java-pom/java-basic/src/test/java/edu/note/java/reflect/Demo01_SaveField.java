package edu.note.java.reflect;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author JackyLee
 * @date 2024/11/28 10:51
 **/
public class Demo01_SaveField {

    @Test
    @DisplayName("保存任意类的成员变量")
    void test01() throws IllegalAccessException, IOException {
        /*
         * 对于任意一个对象，都可以把对象所有的字段名和值，保存到文件中去
         */
        Student s = new Student("小A", 23, 167.5, '男');
        saveObject(s);
    }

    // 把对象里面所有的成员变量名和值保存到本地文件中
    public static void saveObject(Object obj) throws IllegalAccessException, IOException {
        // 1.获取字节码文件的对象
        Class<?> clazz = obj.getClass();
        // 2. 创建IO流
        BufferedWriter bw = new BufferedWriter(new FileWriter("a.txt"));
        // 3. 获取所有的成员变量
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            // 获取成员变量的名字
            String name = field.getName();
            // 获取成员变量的值
            Object value = field.get(obj);
            // 写出数据
            bw.write(name + "=" + value);
            bw.newLine();
        }

        bw.close();

    }
}