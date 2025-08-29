package edu.note.java.xml;

public class A01_xml解析demo {
    // public static void main(String[] args) throws DocumentException {
    //
    //     // 解析器对象
    //     SAXReader reader = new SAXReader();
    //     // 利用解析器去读取xml文件，并返回文本文档
    //     File file = new File("java-basic-note\\src\\day36_03_xml\\person.xml");
    //     Document document = reader.read(file);
    //     System.out.println(document);
    //     // 获得根元素对象
    //     Element rootElement = document.getRootElement();
    //     System.out.println(rootElement);
    //     System.out.println(rootElement.getName());
    //     // 获得根标签的子标签
    //     List<Element> list = rootElement.elements("person");// 可以空参，也可以指定参数
    //     System.out.println(list);
    //     for (Element element : list) {
    //         // 获得id
    //         Attribute id = element.attribute("id");
    //         String idValue = id.getText();
    //         // 获得name
    //         String name = element.element("name").getText();
    //         // 获得age
    //         String age = element.element("age").getText();
    //         System.out.println(idValue+":"+name+","+age);
    //         // 获得age
    //     }
    //
    //
    // }
}
