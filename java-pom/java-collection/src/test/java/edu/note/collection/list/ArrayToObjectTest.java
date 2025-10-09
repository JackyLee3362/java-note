package edu.note.collection.list;

/**
 * ArrayToObjectTest
 * 将数组转为 Object 类型
 */
public class ArrayToObjectTest {

    public static void main(String[] args) {
        int[] numbers = { 1, 2, 3, 4, 5 }; // 定义一个 int 类型的数组

        // 将数组强制转换为 Object 类型
        Object obj = (Object) numbers;

        // 使用 Object 类型引用调用 Object 类的方法
        System.out.println(obj.getClass()); // 输出结果：class [I，表示它是一个 int 类型的数组

        // 尝试使用 Object 类型引用调用数组的特定方法
        // obj.length(); // 这行代码会导致编译错误，因为 Object 类没有 length() 方法

        // 将 Object 类型引用转回数组类型
        int[] newArray = (int[]) obj;

        // 使用数组类型引用调用数组的特定方法
        int arrayLength = newArray.length;
        System.out.println("数组的长度：" + arrayLength); // 输出结果：数组的长度：5

        // todo: Arrays.copyOf 中，为什么需要强制转换
        // (Object)newType == (Object)Object[].class
        Integer[] a = { 1, 2, 3 };
        System.out.println(a);
        System.out.println(Integer[].class);
        System.out.println((Object) Integer[].class);
    }
}