package edu.note.spring;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import lombok.Data;

/**
 * @author jackylee
 * @date 2025-09-30 14:35
 */
@Data
public class HelloServiceImplCollection implements HelloService {
    
    private int[] array;

    private List<String> list;

    private Set<String> set;

    private Map<String,String> map;

    private Properties properties;


    @Override
    public String hello(String name) {
        System.out.println("遍历数组:" + Arrays.toString(array));
        System.out.println("遍历List" + list);
        System.out.println("遍历Set" + set);
        System.out.println("遍历Map" + map);
        System.out.println("遍历Properties" + properties);
        return "HELLO, " + name;
    }

    @Override
    public String bye() {
        return "BYE";
    }

}
