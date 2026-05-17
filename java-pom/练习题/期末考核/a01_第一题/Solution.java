package 其他.d38_期末考核.a01_第一题;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.StringJoiner;
/**
 * 第一题（15）
 * 需求
 * 某小型商城系统的订单信息在**素材下的orders.xml文件中**，现在要求把xml中的订单信息，封装成一个一个的订单对象，将订单对象保存到ArrayList集合中。
 * 具体功能点要求
 * 1)定义订单类Order，创 建ArrayList集合，用于存储订单Order对象 （解析XML 4分，封装成对象2分）
 * 2)请使用Stream流找出今天之前的订单，并遍历输出。（3分）
 * 3)请使用Stream流找出集合中价格最贵的订流单，把这个订单的详细信息打印出来。（3分）
 * 4)请使用Stream流遍历集合中的每个订单，要求按照价格降序输出每个订单的详情。（3分）
 */
public class Solution {
    public static void main(String[] args) throws DocumentException {
        // 1. 解析xml对象
        // 工作路径是项目路径
        File file = new File("java-basic-note\\src\\day38_期末考核\\a01_第一题\\orders.xml");
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        Element rootElement = document.getRootElement();
        ArrayList<Order> list = new ArrayList<>();
        for (Element element : rootElement.elements()) {
            String id = element.attribute("id").getText();
            Integer idValue = Integer.valueOf(id);

            String name = element.element("name").getText();

            String time = element.element("time").getText();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime localDateTime = LocalDateTime.parse(time, formatter);

            String price = element.element("double").getText();
            double priceValue = Double.parseDouble(price);

            StringJoiner sj = new StringJoiner(" ", "[", "]");
            System.out.println(sj.add(id).add(name).add(time).add(price));
            list.add(new Order(idValue, name, localDateTime, priceValue));
        }
        // 遍历打印
        list.stream().forEach(System.out::println);

        System.out.println(list.stream().max((o1, o2) -> (int) (o1.getPrice() * 10 - o2.getPrice() * 10)));
        list.stream().sorted((o1, o2) -> (int) (o2.getPrice() * 10 - o1.getPrice() * 10)).forEach(System.out::println);

    }

    @Test
    public void method1(){
        double a = 1.2;
        double b = 1.1;
        System.out.println((int)(a-b));
    }
}
